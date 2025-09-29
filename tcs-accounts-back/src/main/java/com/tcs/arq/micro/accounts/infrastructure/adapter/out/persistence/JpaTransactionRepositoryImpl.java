package com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence;

import com.tcs.arq.micro.accounts.domain.exception.AccountNotFoundException;
import com.tcs.arq.micro.accounts.domain.model.Transaction;
import com.tcs.arq.micro.accounts.domain.model.TransactionAll;
import com.tcs.arq.micro.accounts.domain.port.out.persistence.repository.TransactionRepositoryPort;
import com.tcs.arq.micro.accounts.domain.port.out.rest.customer.AccountCustomerPort;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.entity.AccountEntity;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.entity.TransactionEntity;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.mapper.TransactionAllMapper;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.mapper.TransactionMapper;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.repository.JpaAccountRepository;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.repository.JpaTransactionRepository;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.rest.customer.CustomerResponse;
import com.tcs.arq.micro.accounts.utils.AccountMessagesUtil;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JpaTransactionRepositoryImpl implements TransactionRepositoryPort {

    private final JpaTransactionRepository jpaTransactionRepository;
    private final JpaAccountRepository jpaAccountRepository;
    private final AccountCustomerPort accountCustomerPort;

    @Override
    public Transaction save(Long accountNumber, Transaction transaction) {
        AccountEntity accountEntity = jpaAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format(AccountMessagesUtil.Message.NOT_FOUND_ID, accountNumber)
                ));

        TransactionEntity entity = TransactionMapper.INSTANCE.toEntity(transaction);
        entity.setAccount(accountEntity);

        TransactionEntity savedTransaction = jpaTransactionRepository.save(entity);
        return TransactionMapper.INSTANCE.toDomain(savedTransaction);
    }

    @Override
    public List<Transaction> findByAccountNumberAndDateBetween(Long accountNumber, LocalDateTime from, LocalDateTime to) {
        return jpaTransactionRepository.findByAccountNumberAndDateBetween(accountNumber, from, to)
                .stream()
                .map(TransactionMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionAll> findByDateBetween(LocalDateTime from, LocalDateTime to) {
        List<TransactionAll> transactionAlls = new ArrayList<>();
        Map<Long, CustomerResponse> customerCache = new HashMap<>();

        List<TransactionEntity> transactionEntities = jpaTransactionRepository.findByAccountNumberAndDateBetweenOrderByDateDesc(null, from, to);
        for(TransactionEntity transactionEntity : transactionEntities) {
            AccountEntity account = transactionEntity.getAccount();
            Long accountNumber = account.getAccountNumber();
            String accountType = account.getAccountType();

            CustomerResponse customerData = customerCache.computeIfAbsent(
                    account.getClientId(),
                    id -> accountCustomerPort.findCustomerById(id)
            );

            TransactionAll transactionAll = TransactionAllMapper.INSTANCE.toModel(transactionEntity, customerData, accountNumber, accountType);

            BigDecimal initialBalance = transactionAll.getAvailableBalance().subtract(transactionAll.getTransactionAmount());
            transactionAll.setInitialBalance(initialBalance);

            transactionAlls.add(transactionAll);

        }

        return transactionAlls;
    }

}
