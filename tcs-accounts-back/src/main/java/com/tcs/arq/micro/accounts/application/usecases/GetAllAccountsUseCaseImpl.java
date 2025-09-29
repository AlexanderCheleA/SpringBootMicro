package com.tcs.arq.micro.accounts.application.usecases;

import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.domain.port.in.usecase.GetAllAccountsUseCase;
import com.tcs.arq.micro.accounts.domain.port.out.persistence.repository.AccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllAccountsUseCaseImpl implements GetAllAccountsUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    @Override
    public List<Account> getAllAccounts(Boolean estado) {
        return accountRepositoryPort.findAllByEstado(estado);
    }
}
