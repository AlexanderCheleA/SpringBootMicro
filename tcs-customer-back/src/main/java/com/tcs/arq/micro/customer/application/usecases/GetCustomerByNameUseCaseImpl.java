package com.tcs.arq.micro.customer.application.usecases;

import com.tcs.arq.micro.customer.domain.exception.CustomerNotFoundException;
import com.tcs.arq.micro.customer.domain.exception.InvalidCustomerIdException;
import com.tcs.arq.micro.customer.domain.model.Customer;
import com.tcs.arq.micro.customer.domain.port.in.usecase.GetCustomerByNameUseCase;
import com.tcs.arq.micro.customer.domain.port.out.persistence.repository.CustomerRepositoryPort;
import com.tcs.arq.micro.customer.utils.CustomerMessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCustomerByNameUseCaseImpl implements GetCustomerByNameUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    @Override
    public Customer getCustomerByName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidCustomerIdException(CustomerMessagesUtil.Message.INVALID_NAME);
        }

        return customerRepositoryPort.findByName(name)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format(CustomerMessagesUtil.Message.NOT_FOUND_NAME, name)
                ));
    }

}
