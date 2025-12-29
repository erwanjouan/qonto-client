package com.theatomicity.qonto.client.rest;

import com.theatomicity.qonto.client.feign.QontoClient;
import com.theatomicity.qonto.client.model.BankAccountDto;
import com.theatomicity.qonto.client.model.OrganizationDto;
import com.theatomicity.qonto.client.model.TransactionDto;
import com.theatomicity.qonto.client.model.TransactionsDto;
import com.theatomicity.qonto.client.sort.SortBy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
public class QontoRestClient {
    
    private final QontoClient qontoClient;

    private Integer totalPages = 0;
    private TransactionsDto cache;

    private void initCache() {
        if (Objects.isNull(this.cache)) {
            this.cache = new TransactionsDto();
            this.cache.setTransactions(new ArrayList<>());
        }
    }

    public List<TransactionDto> fetch(final SortBy sortBy, final LocalDate settledAtFrom, final LocalDate settledAtTo) {
        initCache();
        final OrganizationDto organization = this.qontoClient.getOrganization();
        final List<BankAccountDto> bankAccounts = organization.getBankAccounts();
        final BankAccountDto bankAccountDto = bankAccounts.get(0);
        final String bankAccountId = bankAccountDto.getId();
        int page = 0;
        do {
            page++;
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            final String settledAtFromIso = settledAtFrom.atStartOfDay().atOffset(ZoneOffset.UTC).format(formatter);
            final String settledAtToIso = settledAtTo.atStartOfDay().atOffset(ZoneOffset.UTC).format(formatter);
            final TransactionsDto part = this.qontoClient.getTransactions(bankAccountId, sortBy.getSorter(), settledAtFromIso, settledAtToIso, page);
            final List<TransactionDto> transactions = part.getTransactions();
            this.cache.getTransactions().addAll(transactions);
            this.setTotalPages(part.getMeta().getTotalPages());
        } while (page < this.getTotalPages());
        return this.cache.getTransactions();
    }
}
