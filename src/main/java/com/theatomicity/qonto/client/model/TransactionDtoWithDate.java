package com.theatomicity.qonto.client.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDtoWithDate extends TransactionDto {
    private LocalDate date;
    private String yearMonth;
    private int year;
    private int month;
}
