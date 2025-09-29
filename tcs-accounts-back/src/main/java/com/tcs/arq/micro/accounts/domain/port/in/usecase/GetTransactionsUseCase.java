package com.tcs.arq.micro.accounts.domain.port.in.usecase;

import com.tcs.arq.micro.accounts.domain.dto.TransactionQuery;
import com.tcs.arq.micro.accounts.domain.model.Transaction;
import com.tcs.arq.micro.accounts.domain.model.TransactionAll;

import java.util.List;

public interface GetTransactionsUseCase {

    List<Transaction> getTransactions(TransactionQuery transactionQuery);

    List<TransactionAll> getTransactionsAll(TransactionQuery transactionQuery);

}
