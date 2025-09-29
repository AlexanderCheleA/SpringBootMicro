package com.tcs.arq.micro.customer.application.usecases;

import com.tcs.arq.micro.customer.domain.exception.CustomerAlreadyInactiveException;
import com.tcs.arq.micro.customer.domain.exception.CustomerNotFoundException;
import com.tcs.arq.micro.customer.domain.exception.InvalidCustomerIdException;
import com.tcs.arq.micro.customer.domain.model.Customer;
import com.tcs.arq.micro.customer.domain.port.in.usecase.LogicalDeleteCustomerUseCase;
import com.tcs.arq.micro.customer.domain.port.out.persistence.repository.CustomerRepositoryPort;
import com.tcs.arq.micro.customer.utils.CustomerMessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LogicalDeleteCustomerUseCaseImpl implements LogicalDeleteCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    @Transactional
    @Override
    public Customer logicalDeleteCustomer(Long id) {
        Customer Customer = getCustomerId(id);

        if (Boolean.FALSE.equals(Customer.getStatus())) {
            throw new CustomerAlreadyInactiveException(
                    String.format(CustomerMessagesUtil.Message.ALREADY_INACTIVE, id)
            );
        }

        Customer.setStatus(Boolean.FALSE);
        return customerRepositoryPort.save(Customer);
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
