package com.tcs.arq.micro.customer.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCustomerIdException extends RuntimeException {

    public InvalidCustomerIdException(String message) {
        super(message);
    }

}
