package com.theatomicity.qonto.client.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonTypeName("organization")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrganizationDto {
    private String id;
    private String name;
    private String slug;
    private String legalName;
    private String locale;
    private Double legalShareCapital;
    private String legalCountry;
    private String legalRegistrationDate;
    private String legalForm;
    private String legalAddress;
    private String legalSector;
    private String contractSignedAt;
    private String legalNumber;
    private List<BankAccountDto> bankAccounts;
}
