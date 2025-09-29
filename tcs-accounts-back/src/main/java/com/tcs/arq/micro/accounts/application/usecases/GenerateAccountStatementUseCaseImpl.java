package com.tcs.arq.micro.accounts.application.usecases;

import com.tcs.arq.micro.accounts.domain.dto.AccountStatement;
import com.tcs.arq.micro.accounts.domain.dto.AccountStatementQuery;
import com.tcs.arq.micro.accounts.domain.exception.CustomerNotFoundException;
import com.tcs.arq.micro.accounts.domain.exception.InactiveCustomerException;
import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.domain.model.Transaction;
import com.tcs.arq.micro.accounts.domain.port.in.usecase.GenerateAccountStatementUseCase;
import com.tcs.arq.micro.accounts.domain.port.out.persistence.repository.AccountRepositoryPort;
import com.tcs.arq.micro.accounts.domain.port.out.persistence.repository.TransactionRepositoryPort;
import com.tcs.arq.micro.accounts.domain.port.out.rest.customer.AccountCustomerPort;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.rest.customer.CustomerResponse;
import com.tcs.arq.micro.accounts.utils.AccountMessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GenerateAccountStatementUseCaseImpl implements GenerateAccountStatementUseCase {

    private final AccountRepositoryPort accountRepositoryPort;
    private final TransactionRepositoryPort transactionRepositoryPort;
    private final AccountCustomerPort accountCustomerPort;

    @Override
    public List<AccountStatement> generateAccountStatement(AccountStatementQuery accountStatementQuery) {

        CustomerResponse customerData = accountCustomerPort.findCustomerById(accountStatementQuery.getClientId());

        if (customerData == null || customerData.getClientId() == null) {
            throw new CustomerNotFoundException(AccountMessagesUtil.Message.CUSTOMER_NOT_FOUND);
        }

        if (Boolean.FALSE.equals(customerData.getStatus())) {
            throw new InactiveCustomerException(AccountMessagesUtil.Message.CUSTOMER_INACTIVE);
        }

        List<Account> clientAccounts = accountRepositoryPort.findByClientId(accountStatementQuery.getClientId());

        List<AccountStatement> accountStatements = new ArrayList<>();
        for (Account account : clientAccounts) {
            List<Transaction> transactions = transactionRepositoryPort.findByAccountNumberAndDateBetween(
                    account.getAccountNumber(), accountStatementQuery.getFrom(), accountStatementQuery.getTo()
            );

            AccountStatement statement = new AccountStatement();
            statement.setAccountNumber(account.getAccountNumber());
            statement.setAccountType(account.getAccountType());
            statement.setInitialBalance(account.getInitialBalance());
            statement.setStatus(account.getStatus());
            statement.setTransactions(transactions);
            accountStatements.add(statement);
        }
        return accountStatements;
    }
}
