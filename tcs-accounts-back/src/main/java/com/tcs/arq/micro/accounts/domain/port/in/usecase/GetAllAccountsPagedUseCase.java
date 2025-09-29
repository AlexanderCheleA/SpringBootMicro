package com.tcs.arq.micro.accounts.domain.port.in.usecase;

import com.tcs.arq.micro.accounts.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllAccountsPagedUseCase {

    Page<Account> getAllAccountsPaged(Pageable pageable, Boolean estado);

}
