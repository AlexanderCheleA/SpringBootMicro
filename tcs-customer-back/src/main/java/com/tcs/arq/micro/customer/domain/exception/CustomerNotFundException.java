package com.tcs.arq.micro.customer.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFundException extends RuntimeException {

    public CustomerNotFundException(String message) {
        super(message);
    }

}
