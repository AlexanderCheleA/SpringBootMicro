package com.tcs.arq.micro.customer.domain.port.in.usecase;

import com.tcs.arq.micro.customer.domain.model.Customer;

public interface GetCustomerByNameUseCase {

    Customer getCustomerByName(String name);

}
