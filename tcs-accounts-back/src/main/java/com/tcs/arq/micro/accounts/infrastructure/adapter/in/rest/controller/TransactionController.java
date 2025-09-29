package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.controller;

import com.tcs.arq.micro.accounts.domain.dto.TransactionQuery;
import com.tcs.arq.micro.accounts.domain.exception.TransactionQueryValidationException;
import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.domain.model.Transaction;
import com.tcs.arq.micro.accounts.domain.model.TransactionAll;
import com.tcs.arq.micro.accounts.domain.port.in.usecase.GetTransactionsUseCase;
import com.tcs.arq.micro.accounts.domain.port.in.usecase.RegisterTransactionUseCase;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request.TransactionQueryRequest;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request.TransactionRequest;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.response.AccountResponse;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.response.TransactionAllResponse;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.response.TransactionResponse;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.request.TransactionQueryMapper;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.request.TransactionRequestMapper;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.response.AccountResponseMapper;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.response.TransactionAllResponseMapper;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.response.TransactionResponseMapper;
import com.tcs.arq.micro.accounts.utils.AccountEndpointsName;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(AccountEndpointsName.ControllerTransactionApi.BASE_API)
@RequiredArgsConstructor
public class TransactionController {

    private final RegisterTransactionUseCase registerTransactionUseCase;
    private final GetTransactionsUseCase getTransactionsUseCase;
    private final Validator validator;

    @PostMapping
    public ResponseEntity<AccountResponse> registerTransaction(@RequestBody TransactionRequest transactionRequest) {
        transactionRequest.setDate(LocalDateTime.now());
        Transaction transaction = TransactionRequestMapper.INSTANCE.toModel(transactionRequest);
        Account account = registerTransactionUseCase.registerTransaction(transaction);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AccountResponseMapper.INSTANCE.toDTO(account));
    }

    @GetMapping(AccountEndpointsName.ControllerTransactionApi.ID_IN_PATH_ACCOUNT_NUMBER)
    public ResponseEntity<List<TransactionResponse>> getTransactions(
            @PathVariable Long accountNumber,
            @RequestParam(required = true)
            @NotNull(message = AccountEndpointsName.Validation.FROM_DATE_REQUIRED)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from,

            @RequestParam(required = true)
            @NotNull(message = AccountEndpointsName.Validation.TO_DATE_REQUIRED)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime to
    ) {
        TransactionQueryRequest transactionQueryRequest = new TransactionQueryRequest();
        transactionQueryRequest.setAccountNumber(accountNumber);
        transactionQueryRequest.setFrom(from);
        transactionQueryRequest.setTo(to);

        Set<ConstraintViolation<TransactionQueryRequest>> violations =
                validator.validate(transactionQueryRequest);

        if (!violations.isEmpty()) {
            throw new TransactionQueryValidationException(violations);
        }

        TransactionQuery transactionQuery = TransactionQueryMapper.INSTANCE.toQuery(transactionQueryRequest);
        List<Transaction> transactions = getTransactionsUseCase.getTransactions(transactionQuery);

        return ResponseEntity.ok(TransactionResponseMapper.INSTANCE.toDTOList(transactions));
    }

    @GetMapping(AccountEndpointsName.ControllerTransactionApi.REPORT_ALL)
    public ResponseEntity<List<TransactionAllResponse>> getTransactionsAll(
            @RequestParam(required = true)
            @NotNull(message = AccountEndpointsName.Validation.FROM_DATE_REQUIRED)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from,

            @RequestParam(required = true)
            @NotNull(message = AccountEndpointsName.Validation.TO_DATE_REQUIRED)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime to
    ) {
        TransactionQuery transactionQuery = new TransactionQuery();
        transactionQuery.setFrom(from);
        transactionQuery.setTo(to);

        List<TransactionAll> transactions = getTransactionsUseCase.getTransactionsAll(transactionQuery);

        return ResponseEntity.ok(TransactionAllResponseMapper.INSTANCE.toDTOList(transactions));
    }

}
