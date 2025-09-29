package com.tcs.arq.micro.accounts.domain.port.in.usecase;

import com.tcs.arq.micro.accounts.domain.dto.AccountStatement;
import com.tcs.arq.micro.accounts.domain.dto.AccountStatementQuery;

import java.util.List;

public interface GenerateAccountStatementUseCase {

    List<AccountStatement> generateAccountStatement(AccountStatementQuery accountStatementQuery);

}
