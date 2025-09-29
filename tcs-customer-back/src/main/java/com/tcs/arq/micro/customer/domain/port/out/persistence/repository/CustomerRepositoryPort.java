package com.tcs.arq.micro.customer.domain.port.out.persistence.repository;

import com.tcs.arq.micro.customer.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {

    Optional<Customer> findCustomerById(Long clientId);

    Optional<Customer> findByName(String clientName);

    List<Customer> findByStatus(Boolean status);

    Optional<Customer> findByIdentification(String identification);

    Page<Customer> findAllByStatus(Pageable pageable, Boolean status);

    Customer save(Customer task);

    List<Customer> findAll();

    Customer updateCustomer(Customer customer);

}
