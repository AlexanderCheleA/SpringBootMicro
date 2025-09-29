package com.tcs.arq.micro.accounts.domain.port.out.persistence.repository;

import com.tcs.arq.micro.accounts.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountRepositoryPort {

    Optional<Account> findByAccountNumber(Long accountNumber);
    Account save(Account account);
    Account update(Account account);
    Account updateBalance(Long accountNumber, BigDecimal newBalance);
    Account logicalDelete(Long accountNumber);
    List<Account> findAllByEstado(Boolean estado);
    Page<Account> findAllPaged(Pageable pageable, Boolean estado);
    List<Account> findByClientId(Long clientId);

}

