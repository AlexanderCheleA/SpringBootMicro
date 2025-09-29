package com.tcs.arq.micro.customer.application.usecases;

import com.tcs.arq.micro.customer.domain.exception.CustomerNotFundException;
import com.tcs.arq.micro.customer.domain.model.Customer;
import com.tcs.arq.micro.customer.domain.port.in.usecase.GetAllCustomersUseCase;
import com.tcs.arq.micro.customer.domain.port.out.persistence.repository.CustomerRepositoryPort;
import com.tcs.arq.micro.customer.utils.CustomerMessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllCustomersUseCaseImpl implements GetAllCustomersUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    @Transactional(readOnly = true)
    @Override
    public List<Customer> getAllCustomers(Boolean status) {
        List<Customer> customers;
        if (status == null) {
            customers = customerRepositoryPort.findAll();
        } else {
            customers = customerRepositoryPort.findByStatus(status);
        }

        if (customers.isEmpty()) {
            throw new CustomerNotFundException(CustomerMessagesUtil.Message.NOT_FOUND_CUSTOMERS);
        }

        return customers;
    }

}
