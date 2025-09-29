package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.tcs.arq.micro.accounts.utils.AccountEndpointsName.Validation.*;

@Data
public class TransactionRequest {

    @NotNull(message = ACCOUNT_NUMBER_REQUIRED)
    private Long accountNumber;

    @NotNull(message = AMOUNT_REQUIRED)
    @DecimalMin(value = "0.01", inclusive = true, message = AMOUNT_POSITIVE)
    private BigDecimal amount;

    @Size(max = 200, message = DESCRIPTION_MAX_LENGTH)
    private String description;

    private LocalDateTime date;
}
