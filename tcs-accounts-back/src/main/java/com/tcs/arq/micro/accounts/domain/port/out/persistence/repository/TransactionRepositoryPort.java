package com.tcs.arq.micro.accounts.domain.port.out.persistence.repository;

import com.tcs.arq.micro.accounts.domain.model.Transaction;
import com.tcs.arq.micro.accounts.domain.model.TransactionAll;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepositoryPort {

    Transaction save(Long accountNumber, Transaction transaction);

    List<Transaction> findByAccountNumberAndDateBetween(Long accountNumber, LocalDateTime from, LocalDateTime to);

    List<TransactionAll> findByDateBetween(LocalDateTime from, LocalDateTime to);

}
