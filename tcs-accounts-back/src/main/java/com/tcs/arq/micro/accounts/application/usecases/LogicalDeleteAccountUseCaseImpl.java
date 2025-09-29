package com.tcs.arq.micro.accounts.application.usecases;

import com.tcs.arq.micro.accounts.domain.exception.InvalidAccountIdException;
import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.domain.port.in.usecase.LogicalDeleteAccountUseCase;
import com.tcs.arq.micro.accounts.domain.port.out.persistence.repository.AccountRepositoryPort;
import com.tcs.arq.micro.accounts.utils.AccountMessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LogicalDeleteAccountUseCaseImpl implements LogicalDeleteAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    @Transactional
    @Override
    public Account logicalDeleteAccount(Long accountNumber) {
        if (accountNumber == null || accountNumber <= 0) {
            throw new InvalidAccountIdException(AccountMessagesUtil.Message.INVALID_ID);
        }
        return accountRepositoryPort.logicalDelete(accountNumber);
    }

}
