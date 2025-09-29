package com.tcs.arq.micro.customer.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CustomerAlreadyInactiveException extends RuntimeException {

    public CustomerAlreadyInactiveException(String message) {
        super(message);
    }

}
