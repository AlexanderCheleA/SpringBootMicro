package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

import static com.tcs.arq.micro.accounts.utils.AccountEndpointsName.Validation.*;

@Data
public class AccountByNameRequest {

    @NotBlank(message = CLIENT_NAME_REQUIRED)
    @Size(max = 100, message = CLIENT_NAME_MAX_LENGTH)
    private String clientName;

    @NotBlank(message = ACCOUNT_TYPE_REQUIRED)
    @Size(max = 50, message = ACCOUNT_TYPE_MAX_LENGTH)
    private String accountType;

    @NotNull(message = INITIAL_BALANCE_REQUIRED)
    @DecimalMin(value = "0.00", message = INITIAL_BALANCE_POSITIVE)
    private BigDecimal initialBalance;
}
