package com.tcs.arq.micro.accounts.domain.port.in.usecase;

import com.tcs.arq.micro.accounts.domain.model.Account;

public interface CreateAccountUseCase {

    Account createAccount(Account account);

}
