package com.theatomicity.qonto.client.batch;

import com.theatomicity.qonto.client.feign.QontoClient;
import com.theatomicity.qonto.client.model.BankAccountDto;
import com.theatomicity.qonto.client.model.OrganizationDto;
import com.theatomicity.qonto.client.model.TransactionDto;
import com.theatomicity.qonto.client.model.TransactionsDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Getter
@Setter
@RequiredArgsConstructor
public class QontoBatchReader {

    private static final Logger logger = Logger.getLogger(QontoBatchReader.class.getSimpleName());

    private final QontoClient qontoClient;

    private Integer totalPages = 0;

    private TransactionsDto cache;
    private Integer transactionIndex;

    // custom utility for spring batch
    public TransactionDto read() {
        if (Objects.isNull(this.cache)) {
            this.cache = new TransactionsDto();
            this.cache.setTransactions(new ArrayList<>());
            this.fetch();
            this.transactionIndex = -1;
        }
        this.transactionIndex++;
        final int size = this.cache.getTransactions().size();
        if (this.transactionIndex >= size) {
            return null;
        }
        final List<TransactionDto> transactionsDtos = this.cache.getTransactions();
        return transactionsDtos.get(this.transactionIndex);
    }

    // custom utility for spring batch
    private void fetch() {
        final OrganizationDto organization = this.qontoClient.getOrganization();
        final List<BankAccountDto> bankAccounts = organization.getBankAccounts();
        final BankAccountDto bankAccountDto = bankAccounts.get(0);
        final String bankAccountId = bankAccountDto.getId();
        final String sortBy = "settled_at:asc";
        int page = 0;
        do {
            page++;
            final List<TransactionDto> transactions = new ArrayList<>();
            final TransactionsDto part = this.qontoClient.getTransactions(bankAccountId, sortBy, null, null, page);
            transactions.addAll(part.getTransactions());
            final int size = part.getTransactions().size();
            final String message = String.format("retrieved page %d transactions %d", page, size);
            logger.info(message);
            this.cache.getTransactions().addAll(transactions);
            this.setTotalPages(part.getMeta().getTotalPages());
        } while (page < this.getTotalPages());
    }
}
