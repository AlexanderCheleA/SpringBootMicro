package com.tcs.arq.micro.accounts.application.usecases;

import com.tcs.arq.micro.accounts.domain.dto.TransactionQuery;
import com.tcs.arq.micro.accounts.domain.model.Transaction;
import com.tcs.arq.micro.accounts.domain.model.TransactionAll;
import com.tcs.arq.micro.accounts.domain.port.in.usecase.GetTransactionsUseCase;
import com.tcs.arq.micro.accounts.domain.port.out.persistence.repository.TransactionRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetTransactionsUseCaseImpl implements GetTransactionsUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    @Override
    public List<Transaction> getTransactions(TransactionQuery transactionQuery) {
        return transactionRepositoryPort.findByAccountNumberAndDateBetween(
                transactionQuery.getAccountNumber(), transactionQuery.getFrom(), transactionQuery.getTo()
        );
    }

    @Override
    public List<TransactionAll> getTransactionsAll(TransactionQuery transactionQuery) {
        return transactionRepositoryPort.findByDateBetween(transactionQuery.getFrom(), transactionQuery.getTo());
    }
}
