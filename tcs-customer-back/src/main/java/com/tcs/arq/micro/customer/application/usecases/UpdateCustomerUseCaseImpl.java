package com.tcs.arq.micro.customer.application.usecases;

import com.tcs.arq.micro.customer.domain.exception.CustomerNotFoundException;
import com.tcs.arq.micro.customer.domain.exception.InvalidCustomerIdException;
import com.tcs.arq.micro.customer.domain.model.Customer;
import com.tcs.arq.micro.customer.domain.port.in.usecase.UpdateCustomerUseCase;
import com.tcs.arq.micro.customer.domain.port.out.persistence.repository.CustomerRepositoryPort;
import com.tcs.arq.micro.customer.utils.CustomerMessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    @Transactional
    @Override
    public Customer updateCustomer(Customer customer) {
        Customer existingCustomer = getCustomerId(customer.getClientId());

        existingCustomer.setName(customer.getName());
        existingCustomer.setGender(customer.getGender());
        existingCustomer.setAge(customer.getAge());
        existingCustomer.setIdentification(customer.getIdentification());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setPassword(customer.getPassword());
        existingCustomer.setStatus(customer.getStatus());

        return customerRepositoryPort.save(existingCustomer);
    }

    private Customer getCustomerId(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidCustomerIdException(CustomerMessagesUtil.Message.INVALID_ID);
        }

        return customerRepositoryPort.findCustomerById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format(CustomerMessagesUtil.Message.NOT_FOUND_ID, id)
                ));
    }
}
