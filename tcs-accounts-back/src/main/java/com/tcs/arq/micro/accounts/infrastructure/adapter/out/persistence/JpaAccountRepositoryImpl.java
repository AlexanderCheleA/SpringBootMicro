package com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence;

import com.tcs.arq.micro.accounts.domain.exception.AccountNotFoundException;
import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.domain.port.out.persistence.repository.AccountRepositoryPort;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.entity.AccountEntity;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.mapper.AccountMapper;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.repository.JpaAccountRepository;
import com.tcs.arq.micro.accounts.utils.AccountMessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JpaAccountRepositoryImpl implements AccountRepositoryPort {

    private final JpaAccountRepository jpaAccountRepository;

    @Override
    public Optional<Account> findByAccountNumber(Long accountNumber) {
        return jpaAccountRepository.findByAccountNumber(accountNumber)
                .map(AccountMapper.INSTANCE::toDomain);
    }

    @Override
    public Account save(Account account) {
        AccountEntity entity = AccountMapper.INSTANCE.toEntity(account);
        AccountEntity saveAccount = jpaAccountRepository.save(entity);
        return AccountMapper.INSTANCE.toDomain(saveAccount);
    }

    @Override
    public Account update(Account account) {
        AccountEntity entity = AccountMapper.INSTANCE.toEntity(account);
        AccountEntity updated = jpaAccountRepository.save(entity);
        return AccountMapper.INSTANCE.toDomain(updated);
    }

    @Override
    public Account updateBalance(Long accountNumber, BigDecimal newBalance) {
        AccountEntity account = jpaAccountRepository.findById(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format(AccountMessagesUtil.Message.NOT_FOUND_ID, accountNumber)
                ));
        account.setInitialBalance(newBalance);

        AccountEntity updated = jpaAccountRepository.save(account);

        return AccountMapper.INSTANCE.toDomain(updated);
    }

    @Override
    public Account logicalDelete(Long accountNumber) {
        AccountEntity account = jpaAccountRepository.findById(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format(AccountMessagesUtil.Message.NOT_FOUND_ID, accountNumber)
                ));

        account.setStatus(Boolean.FALSE);
        AccountEntity updated = jpaAccountRepository.save(account);

        return AccountMapper.INSTANCE.toDomain(updated);
    }


    @Override
    public List<Account> findAllByEstado(Boolean estado) {
        return jpaAccountRepository.findByStatus(estado)
                .stream()
                .map(AccountMapper.INSTANCE::toDomain)
                .toList();
    }

    @Override
    public Page<Account> findAllPaged(Pageable pageable, Boolean estado) {
        return jpaAccountRepository.findByStatusPaged(estado, pageable)
                .map(AccountMapper.INSTANCE::toDomain);
    }

    @Override
    public List<Account> findByClientId(Long clientId) {
        return jpaAccountRepository.findByClientId(clientId)
                .stream()
                .map(AccountMapper.INSTANCE::toDomain)
                .toList();
    }
}
