package com.tcs.arq.micro.accounts.domain.port.in.usecase;

import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.domain.model.Transaction;

public interface RegisterTransactionUseCase {

    Account registerTransaction(Transaction transaction);

}
