package com.tcs.arq.micro.accounts.application.usecases;

import com.tcs.arq.micro.accounts.domain.dto.TransactionType;
import com.tcs.arq.micro.accounts.domain.exception.AccountAlreadyInactiveException;
import com.tcs.arq.micro.accounts.domain.exception.AccountNotFoundException;
import com.tcs.arq.micro.accounts.domain.exception.InsufficientFundsException;
import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.domain.model.Transaction;
import com.tcs.arq.micro.accounts.domain.port.in.usecase.RegisterTransactionUseCase;
import com.tcs.arq.micro.accounts.domain.port.out.persistence.repository.AccountRepositoryPort;
import com.tcs.arq.micro.accounts.domain.port.out.persistence.repository.TransactionRepositoryPort;
import com.tcs.arq.micro.accounts.utils.AccountMessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class RegisterTransactionUseCaseImpl implements RegisterTransactionUseCase {

    private final AccountRepositoryPort accountRepositoryPort;
    private final TransactionRepositoryPort transactionRepositoryPort;

    @Transactional
    @Override
    public Account registerTransaction(Transaction transaction) {
        Account account = accountRepositoryPort.findByAccountNumber(transaction.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format(AccountMessagesUtil.Message.ACCOUNT_NOT_FOUND, transaction.getAccountNumber())
                ));

        if (Boolean.FALSE.equals(account.getStatus())) {
            throw new AccountAlreadyInactiveException(
                    String.format(AccountMessagesUtil.Message.ALREADY_INACTIVE, transaction.getAccountNumber())
            );
        }

        BigDecimal currentBalance = account.getInitialBalance();
        BigDecimal newBalance = currentBalance.add(transaction.getAmount());
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException(AccountMessagesUtil.Message.INSUFFICIENT_FUNDS);
        }

        String type = transaction.getAmount().compareTo(BigDecimal.ZERO) < 0
                ? TransactionType.RETIRO.name()
                : TransactionType.DEPOSITO.name();

        transaction.setTransactionType(type);
        transaction.setBalance(newBalance);
        transaction.setDate(LocalDateTime.now());

        transactionRepositoryPort.save(account.getAccountNumber(), transaction);

        Account accountUpdate = accountRepositoryPort.updateBalance(account.getAccountNumber(), newBalance);

        return accountUpdate;
    }
}