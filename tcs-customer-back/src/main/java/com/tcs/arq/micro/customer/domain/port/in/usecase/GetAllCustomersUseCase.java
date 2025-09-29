package com.tcs.arq.micro.customer.domain.port.in.usecase;

import com.tcs.arq.micro.customer.domain.model.Customer;

import java.util.List;

public interface GetAllCustomersUseCase {

    List<Customer> getAllCustomers(Boolean status);

}
