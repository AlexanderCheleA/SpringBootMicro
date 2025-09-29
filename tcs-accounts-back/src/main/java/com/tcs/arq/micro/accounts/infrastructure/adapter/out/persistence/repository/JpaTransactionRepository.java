package com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.repository;

import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaTransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("""
        SELECT t FROM TransactionEntity t
        WHERE (:accountNumber IS NULL OR t.account.accountNumber = :accountNumber) AND t.date BETWEEN :from AND :to
    """)
    List<TransactionEntity> findByAccountNumberAndDateBetween(
            @Param("accountNumber") Long accountNumber,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );

    @Query("""
    SELECT t FROM TransactionEntity t
    WHERE (:accountNumber IS NULL OR t.account.accountNumber = :accountNumber)
    AND t.date BETWEEN :from AND :to
    ORDER BY t.date DESC
""")
    List<TransactionEntity> findByAccountNumberAndDateBetweenOrderByDateDesc(
            @Param("accountNumber") Long accountNumber,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );

}
