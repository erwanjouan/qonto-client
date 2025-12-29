package com.theatomicity.qonto.client.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDtoWithAmounts extends TransactionDtoWithDate {
    private BigDecimal grossAmount;
    private BigDecimal netAmount;
    private BigDecimal yearBalance;
    private BigDecimal globalBalance;
}
