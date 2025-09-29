package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

import static com.tcs.arq.micro.accounts.utils.AccountEndpointsName.Validation.*;

@Data
public class TransactionQueryRequest {

    @NotNull(message = ACCOUNT_NUMBER_REQUIRED)
    private Long accountNumber;

    @NotNull(message = FROM_DATE_REQUIRED)
    private LocalDateTime from;

    @NotNull(message = TO_DATE_REQUIRED)
    private LocalDateTime to;

}
