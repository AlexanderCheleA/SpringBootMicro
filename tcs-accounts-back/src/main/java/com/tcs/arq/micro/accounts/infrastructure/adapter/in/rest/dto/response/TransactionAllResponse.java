package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionAllResponse {

    private String date;
    private String clientName;
    private Long accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private Boolean status;
    private BigDecimal transactionAmount;
    private BigDecimal availableBalance;
}

