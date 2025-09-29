package com.tcs.arq.micro.accounts.application.usecases;

import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.domain.port.in.usecase.GetAllAccountsPagedUseCase;
import com.tcs.arq.micro.accounts.domain.port.out.persistence.repository.AccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAllAccountsPagedUseCaseImpl implements GetAllAccountsPagedUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    @Override
    public Page<Account> getAllAccountsPaged(Pageable pageable, Boolean estado) {
        return accountRepositoryPort.findAllPaged(pageable, estado);
    }

}
