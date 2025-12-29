package com.theatomicity.qonto.client;

import com.theatomicity.qonto.client.model.TransactionDto;
import com.theatomicity.qonto.client.rest.QontoRestClient;
import com.theatomicity.qonto.client.sort.SortBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class TestApplication implements CommandLineRunner {
    public static void main(final String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    public static final LocalDate FROM = LocalDate.of(2025, 10, 1);
    public static final LocalDate TO = LocalDate.of(2025, 12, 31);

    @Autowired
    private QontoRestClient qontoCustomClient;

    @Override
    public void run(final String... args) throws Exception {
        final List<TransactionDto> transactionDtos = this.qontoCustomClient.fetch(SortBy.SETTLED_AT_ASC, FROM, TO);
        for (final TransactionDto transactionDto : transactionDtos) {
            System.out.println(transactionDto);
        }

    }
}
