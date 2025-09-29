package com.tcs.arq.micro.accounts.domain.port.out.rest.customer;

import com.tcs.arq.micro.accounts.infrastructure.adapter.out.rest.customer.CustomerResponse;

public interface AccountCustomerPort {

    CustomerResponse findCustomerById(Long customerId);

    CustomerResponse findCustomerByName(String clientName);

}
