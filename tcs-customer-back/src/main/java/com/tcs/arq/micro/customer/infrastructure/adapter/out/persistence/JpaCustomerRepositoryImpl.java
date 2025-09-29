package com.tcs.arq.micro.customer.infrastructure.adapter.out.persistence;

import com.tcs.arq.micro.customer.domain.model.Customer;
import com.tcs.arq.micro.customer.domain.port.out.persistence.repository.CustomerRepositoryPort;
import com.tcs.arq.micro.customer.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import com.tcs.arq.micro.customer.infrastructure.adapter.out.persistence.mapper.CustomerMapper;
import com.tcs.arq.micro.customer.infrastructure.adapter.out.persistence.repository.JpaCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JpaCustomerRepositoryImpl implements CustomerRepositoryPort {

    private final JpaCustomerRepository jpaCustomerRepository;

    @Override
    public Optional<Customer> findCustomerById(Long clientId) {
        return jpaCustomerRepository.findByClientId(clientId)
                .map(CustomerMapper.INSTANCE::toDomain);
    }

    @Override
    public Optional<Customer> findByName(String clientName) {
        return jpaCustomerRepository.findByName(clientName)
                .map(CustomerMapper.INSTANCE::toDomain);
    }

    @Override
    public List<Customer> findByStatus(Boolean status) {
        return jpaCustomerRepository.findByStatus(status)
                .stream()
                .map(CustomerMapper.INSTANCE::toDomain)
                .toList();
    }

    @Override
    public Optional<Customer> findByIdentification(String identification) {
        return jpaCustomerRepository.findByIdentification(identification)
                .map(CustomerMapper.INSTANCE::toDomain);
    }

    @Override
    public Page<Customer> findAllByStatus(Pageable pageable, Boolean status) {
        Page<CustomerEntity> entityPage = jpaCustomerRepository.findAllByStatus(pageable, status);
        return entityPage.map(CustomerMapper.INSTANCE::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = CustomerMapper.INSTANCE.toEntity(customer);
        CustomerEntity savedCustomer = jpaCustomerRepository.save(entity);
        return CustomerMapper.INSTANCE.toDomain(savedCustomer);
    }

    @Override
    public List<Customer> findAll() {
        return jpaCustomerRepository.findAll().stream()
                .map(CustomerMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }
}
