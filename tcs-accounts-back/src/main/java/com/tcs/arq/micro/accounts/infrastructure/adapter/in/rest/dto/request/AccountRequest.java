package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

import static com.tcs.arq.micro.accounts.utils.AccountEndpointsName.Validation.*;

@Data
public class AccountRequest {

    @NotBlank(message = ACCOUNT_TYPE_REQUIRED)
    private String accountType;

    @NotNull(message = INITIAL_BALANCE_REQUIRED)
    @DecimalMin(value = "0.00", message = INITIAL_BALANCE_POSITIVE)
    private BigDecimal initialBalance;

    @NotNull(message = CLIENT_ID_REQUIRED)
    private Long clientId;

}
