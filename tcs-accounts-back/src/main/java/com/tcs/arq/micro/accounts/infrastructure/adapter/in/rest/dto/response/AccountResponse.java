package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountResponse {

    private Long accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private Boolean status;
    private Long clientId;

}
