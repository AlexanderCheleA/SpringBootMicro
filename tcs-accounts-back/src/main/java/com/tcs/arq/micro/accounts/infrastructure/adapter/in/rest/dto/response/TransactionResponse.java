package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {

    private Long id;
    private Long accountNumber;
    private BigDecimal amount;
    private String description;
    private LocalDateTime date;

}
