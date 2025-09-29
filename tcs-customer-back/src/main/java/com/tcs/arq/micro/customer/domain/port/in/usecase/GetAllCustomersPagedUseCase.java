package com.tcs.arq.micro.customer.domain.port.in.usecase;

import com.tcs.arq.micro.customer.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllCustomersPagedUseCase {

    Page<Customer> getAllCustomersPaged(Pageable pageable, Boolean status);

}
