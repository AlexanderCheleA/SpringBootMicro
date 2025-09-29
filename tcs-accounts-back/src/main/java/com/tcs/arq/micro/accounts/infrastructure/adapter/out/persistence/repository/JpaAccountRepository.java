package com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.repository;

import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByAccountNumber(Long accountNumber);

    List<AccountEntity> findByClientId(Long clientId);

    List<AccountEntity> findByStatus(Boolean status);

    @Query("SELECT a FROM AccountEntity a WHERE a.status = :status")
    Page<AccountEntity> findByStatusPaged(@Param("status") Boolean status, Pageable pageable);

}
