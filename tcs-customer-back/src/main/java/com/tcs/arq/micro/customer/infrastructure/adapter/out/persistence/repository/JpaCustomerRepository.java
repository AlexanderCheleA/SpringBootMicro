package com.tcs.arq.micro.customer.infrastructure.adapter.out.persistence.repository;

import com.tcs.arq.micro.customer.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, Long> {


    @Query("SELECT c FROM CustomerEntity c WHERE c.name = :name")
    Optional<CustomerEntity> findByName(@Param("name") String name);

    Optional<CustomerEntity> findByClientId(Long clientId);

    List<CustomerEntity> findByStatus(Boolean status);

    @Query("SELECT c FROM CustomerEntity c WHERE c.identification = :identification")
    Optional<CustomerEntity> findByIdentification(@Param("identification") String identification);

    @Query("SELECT c FROM CustomerEntity c WHERE (:status IS NULL OR c.status = :status)")
    Page<CustomerEntity> findAllByStatus(Pageable pageable, @Param("status") Boolean status);

}
