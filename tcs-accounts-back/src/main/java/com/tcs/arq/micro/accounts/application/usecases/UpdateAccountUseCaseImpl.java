package com.tcs.arq.micro.accounts.application.usecases;

import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.domain.port.in.usecase.UpdateAccountUseCase;
import com.tcs.arq.micro.accounts.domain.port.out.persistence.repository.AccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    @Transactional
    @Override
    public Account updateAccount(Account account) {
        return accountRepositoryPort.update(account);
    }

}
