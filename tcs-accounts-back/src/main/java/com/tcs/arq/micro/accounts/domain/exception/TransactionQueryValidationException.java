package com.tcs.arq.micro.accounts.domain.exception;

import com.tcs.arq.micro.accounts.utils.AccountMessagesUtil;
import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class TransactionQueryValidationException extends RuntimeException {

    private final Set<? extends ConstraintViolation<?>> violations;

    public TransactionQueryValidationException(Set<? extends ConstraintViolation<?>> violations) {
        super(AccountMessagesUtil.Message.TRANSACTION_QUERY_VALIDATION_ERROR);
        this.violations = violations;
    }
}
