package com.theatomicity.qonto.client.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BankAccountDto {
    private String id;
    private String slug;
    private String iban;
    private String bic;
    private String currency;
    private Double balance;
    private Integer balanceCents;
    private Double authorizedBalance;
    private Integer authorizedBalanceCents;
    private String name;
    private String updatedAt;
    private String status;
    private Boolean main;
    private Boolean isExternalAccount;
    private String accountNumber;
}
