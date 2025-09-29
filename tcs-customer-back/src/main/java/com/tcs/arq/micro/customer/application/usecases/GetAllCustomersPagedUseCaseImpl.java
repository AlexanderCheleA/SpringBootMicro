package com.tcs.arq.micro.customer.application.usecases;

import com.tcs.arq.micro.customer.domain.exception.CustomerNotFoundException;
import com.tcs.arq.micro.customer.domain.model.Customer;
import com.tcs.arq.micro.customer.domain.port.in.usecase.GetAllCustomersPagedUseCase;
import com.tcs.arq.micro.customer.domain.port.out.persistence.repository.CustomerRepositoryPort;
import com.tcs.arq.micro.customer.utils.CustomerMessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetAllCustomersPagedUseCaseImpl implements GetAllCustomersPagedUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    @Transactional(readOnly = true)
    @Override
    public Page<Customer> getAllCustomersPaged(Pageable pageable, Boolean status) {
        Page<Customer> customerPage = customerRepositoryPort.findAllByStatus(pageable, status);

        if (customerPage.isEmpty()) {
            throw new CustomerNotFoundException(CustomerMessagesUtil.Message.NOT_FOUND_CUSTOMERS);
        }

        return customerPage;
    }

}
