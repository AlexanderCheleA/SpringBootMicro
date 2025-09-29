package com.tcs.arq.micro.customer.application.usecases;

import com.tcs.arq.micro.customer.domain.exception.CustomerAlreadyExistsException;
import com.tcs.arq.micro.customer.domain.model.Customer;
import com.tcs.arq.micro.customer.domain.port.in.usecase.CreateCustomerUseCase;
import com.tcs.arq.micro.customer.domain.port.out.persistence.repository.CustomerRepositoryPort;
import com.tcs.arq.micro.customer.utils.CustomerMessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    @Transactional
    @Override
    public Customer createCustomer(Customer customer) {
        Optional<Customer> existingCustomerOpt = customerRepositoryPort.findByIdentification(customer.getIdentification());
        Customer customerSave = null;

        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();

            if (Boolean.TRUE.equals(existingCustomer.getStatus())) {
                throw new CustomerAlreadyExistsException(
                        String.format(CustomerMessagesUtil.Message.IDENTIFICATION_ALREADY_EXISTS, existingCustomer.getIdentification())
                );
            }

            if (Boolean.FALSE.equals(existingCustomer.getStatus())) {
                existingCustomer.setName(customer.getName());
                existingCustomer.setGender(customer.getGender());
                existingCustomer.setAge(customer.getAge());
                existingCustomer.setAddress(customer.getAddress());
                existingCustomer.setPhone(customer.getPhone());
                existingCustomer.setPassword(customer.getPassword());
                existingCustomer.setStatus(Boolean.TRUE);

                customerSave = customerRepositoryPort.save(existingCustomer);
            }
        } else {
            customerSave = customerRepositoryPort.save(customer);
        }

        return customerSave;
    }

}
