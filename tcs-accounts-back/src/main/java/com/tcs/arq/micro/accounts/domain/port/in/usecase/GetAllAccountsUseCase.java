package com.tcs.arq.micro.accounts.domain.port.in.usecase;

import com.tcs.arq.micro.accounts.domain.model.Account;

import java.util.List;

public interface GetAllAccountsUseCase {

    List<Account> getAllAccounts(Boolean estado);

}
