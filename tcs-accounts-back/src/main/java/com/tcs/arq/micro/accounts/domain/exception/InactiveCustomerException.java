package com.tcs.arq.micro.accounts.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InactiveCustomerException extends RuntimeException {

    public InactiveCustomerException(String message) {
        super(message);
    }
}
