package com.tcs.arq.micro.customer.domain.port.in.usecase;

import com.tcs.arq.micro.customer.domain.model.Customer;

public interface CreateCustomerUseCase {

    Customer createCustomer(Customer customer);

}
