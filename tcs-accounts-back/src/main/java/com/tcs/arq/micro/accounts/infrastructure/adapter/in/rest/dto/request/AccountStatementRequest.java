package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

import static com.tcs.arq.micro.accounts.utils.AccountEndpointsName.Validation.*;

@Data
public class AccountStatementRequest {

    @NotNull(message = CLIENT_ID_REQUIRED)
    private Long clientId;

    @NotNull(message = FROM_DATE_REQUIRED)
    private LocalDateTime from;

    @NotNull(message = TO_DATE_REQUIRED)
    private LocalDateTime to;

}
