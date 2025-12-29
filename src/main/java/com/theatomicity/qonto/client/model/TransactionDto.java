package com.theatomicity.qonto.client.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionDto {
    private String id;
    private String transactionId;
    private BigDecimal amount;
    private Integer amountCents;
    private BigDecimal settledBalance;
    private Integer settledBalanceCents;
    private List<String> attachmentIds;
    private BigDecimal localAmount;
    private BigDecimal localAmountCents;
    private String side;
    private String operationType;
    private String currency;
    private String localCurrency;
    private String label;
    private LocalDateTime settledAt;
    private LocalDateTime emittedAt;
    private LocalDateTime updatedAt;
    private String status;
    private String note;
    private String reference;
    private BigDecimal vatAmount;
    private Integer vatAmountCents;
    private BigDecimal vatRate;
    private String initiatorId;
    private List<String> labelIds;
    private Boolean attachmenLost;
    private Boolean attachmenRequired;
    private String cardLastDigits;
    private String category;
    private String subjectType;
    private String bankAccountId;
    private Boolean isExternalTransaction;
    private IncomeDto income;
}
