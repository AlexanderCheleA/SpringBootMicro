package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAccountResponse {

    private String message;
    private AccountResponse cuenta;
}
