package com.tcs.arq.micro.accounts.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingClientReferenceException extends RuntimeException {

    public MissingClientReferenceException(String message) {
        super(message);
    }
}
