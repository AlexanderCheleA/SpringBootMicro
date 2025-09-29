package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.controller;

import com.tcs.arq.micro.accounts.domain.dto.AccountStatement;
import com.tcs.arq.micro.accounts.domain.dto.AccountStatementQuery;
import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.domain.port.in.usecase.*;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request.AccountByNameRequest;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request.AccountRequest;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request.AccountStatementRequest;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.response.AccountResponse;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.response.DeleteAccountResponse;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.request.AccountByNameRequestMapper;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.request.AccountRequestMapper;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.request.AccountStatementQueryMapper;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.response.AccountResponseMapper;
import com.tcs.arq.micro.accounts.utils.AccountEndpointsName;
import com.tcs.arq.micro.accounts.utils.AccountMessagesUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AccountEndpointsName.ControllerAccountApi.BASE_API)
@RequiredArgsConstructor
public class AccountController {

    private final GenerateAccountStatementUseCase generateAccountStatementUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountByIdUseCase getAccountByIdUseCase;
    private final GetAllAccountsUseCase getAllAccountsUseCase;
    private final GetAllAccountsPagedUseCase getAllAccountsPagedUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final LogicalDeleteAccountUseCase logicalDeleteAccountUseCase;

    @PostMapping(AccountEndpointsName.ControllerAccountApi.REPORT)
    public ResponseEntity<List<AccountStatement>> generateStatement(@RequestBody @Valid AccountStatementRequest request) {
        AccountStatementQuery accountStatementQuery = AccountStatementQueryMapper.INSTANCE.toQuery(request);
        List<AccountStatement> accountStatementResponses = generateAccountStatementUseCase.generateAccountStatement(accountStatementQuery);
        return ResponseEntity.ok(accountStatementResponses);
    }

    @PostMapping(AccountEndpointsName.ControllerAccountApi.CREATE)
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid AccountRequest request) {
        Account accountModel = AccountRequestMapper.INSTANCE.toModel(request);
        accountModel.setStatus(Boolean.TRUE);
        Account created = createAccountUseCase.createAccount(accountModel);
        AccountResponse response = AccountResponseMapper.INSTANCE.toDTO(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(AccountEndpointsName.ControllerAccountApi.CREATE_BY_NAME)
    public ResponseEntity<AccountResponse> createAccountByClientName(@RequestBody @Valid AccountByNameRequest request) {
        Account accountModel = AccountByNameRequestMapper.INSTANCE.toModel(request);
        accountModel.setClientId(null);
        Account created = createAccountUseCase.createAccount(accountModel);
        AccountResponse response = AccountResponseMapper.INSTANCE.toDTO(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping(AccountEndpointsName.ControllerAccountApi.ID_IN_PATH_ACCOUNT)
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long accountNumber) {
        Account account = getAccountByIdUseCase.getAccountById(accountNumber);
        AccountResponse response = AccountResponseMapper.INSTANCE.toDTO(account);
        return ResponseEntity.ok(response);
    }

    @GetMapping(AccountEndpointsName.ControllerAccountApi.ALL)
    public ResponseEntity<List<AccountResponse>> getAllAccounts(@RequestParam(required = false) Boolean estado) {
        List<Account> accounts = getAllAccountsUseCase.getAllAccounts(estado);
        List<AccountResponse> responses = AccountResponseMapper.INSTANCE.toDTOList(accounts);
        return ResponseEntity.ok(responses);
    }

    @GetMapping
    public ResponseEntity<Page<AccountResponse>> getAllAccountsPaged(
            @RequestParam(defaultValue = AccountEndpointsName.ControllerAccountApi.DEFAULT_PAGE_INIT) int page,
            @RequestParam(defaultValue = AccountEndpointsName.ControllerAccountApi.DEFAULT_PAGE_FINAL) int size,
            @RequestParam(defaultValue = AccountEndpointsName.ControllerAccountApi.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(defaultValue = AccountEndpointsName.ControllerAccountApi.DEFAULT_DIRECTION) String direction,
            @RequestParam(required = false) Boolean estado
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Account> accountPage = getAllAccountsPagedUseCase.getAllAccountsPaged(pageable, estado);
        Page<AccountResponse> responsePage = accountPage.map(AccountResponseMapper.INSTANCE::toDTO);

        return ResponseEntity.ok(responsePage);
    }

    @PutMapping(AccountEndpointsName.ControllerAccountApi.ID_IN_PATH_ACCOUNT)
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable Long accountNumber, @RequestBody @Valid AccountRequest request) {
        Account accountModel = AccountRequestMapper.INSTANCE.toModel(request);
        accountModel.setAccountNumber(accountNumber);

        Account updated = updateAccountUseCase.updateAccount(accountModel);
        AccountResponse response = AccountResponseMapper.INSTANCE.toDTO(updated);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(AccountEndpointsName.ControllerAccountApi.ID_IN_PATH_ACCOUNT)
    public ResponseEntity<DeleteAccountResponse> deleteAccount(@PathVariable Long accountNumber) {
        Account deleted = logicalDeleteAccountUseCase.logicalDeleteAccount(accountNumber);
        AccountResponse response = AccountResponseMapper.INSTANCE.toDTO(deleted);
        DeleteAccountResponse result = new DeleteAccountResponse(AccountMessagesUtil.Message.ACCOUNT_DELETED_SUCCESSFULLY, response);
        return ResponseEntity.ok(result);
    }


}
