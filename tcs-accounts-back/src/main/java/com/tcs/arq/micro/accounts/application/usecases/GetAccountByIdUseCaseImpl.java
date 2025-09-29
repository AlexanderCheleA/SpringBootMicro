package com.tcs.arq.micro.accounts.application.usecases;

import com.tcs.arq.micro.accounts.domain.exception.AccountNotFoundException;
import com.tcs.arq.micro.accounts.domain.exception.InvalidAccountIdException;
import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.domain.port.in.usecase.GetAccountByIdUseCase;
import com.tcs.arq.micro.accounts.domain.port.out.persistence.repository.AccountRepositoryPort;
import com.tcs.arq.micro.accounts.utils.AccountMessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAccountByIdUseCaseImpl implements GetAccountByIdUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    @Override
    public Account getAccountById(Long accountNumber) {
        if (accountNumber == null || accountNumber <= 0) {
            throw new InvalidAccountIdException(AccountMessagesUtil.Message.INVALID_ID);
        }

        return accountRepositoryPort.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format(AccountMessagesUtil.Message.NOT_FOUND_ID, accountNumber)
                ));
    }

}
