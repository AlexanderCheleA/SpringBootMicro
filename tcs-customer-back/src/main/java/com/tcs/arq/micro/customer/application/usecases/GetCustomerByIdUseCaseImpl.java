package com.tcs.arq.micro.customer.application.usecases;

import com.tcs.arq.micro.customer.domain.exception.CustomerNotFoundException;
import com.tcs.arq.micro.customer.domain.exception.InvalidCustomerIdException;
import com.tcs.arq.micro.customer.domain.model.Customer;
import com.tcs.arq.micro.customer.domain.port.in.usecase.GetCustomerByIdUseCase;
import com.tcs.arq.micro.customer.domain.port.out.persistence.repository.CustomerRepositoryPort;
import com.tcs.arq.micro.customer.utils.CustomerMessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCustomerByIdUseCaseImpl implements GetCustomerByIdUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    @Override
    public Customer findCustomerById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidCustomerIdException(CustomerMessagesUtil.Message.INVALID_ID);
        }

        return customerRepositoryPort.findCustomerById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format(CustomerMessagesUtil.Message.NOT_FOUND_ID, id)
                ));
    }
}
