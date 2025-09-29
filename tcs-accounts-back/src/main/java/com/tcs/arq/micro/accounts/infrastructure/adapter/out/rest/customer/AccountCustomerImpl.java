package com.tcs.arq.micro.accounts.infrastructure.adapter.out.rest.customer;

import com.tcs.arq.micro.accounts.domain.port.out.rest.customer.AccountCustomerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class AccountCustomerImpl implements AccountCustomerPort {

    private final RestTemplate restTemplate;

    private final String customerServiceUrl;

    @Override
    public CustomerResponse findCustomerById(Long customerId) {
        String url = customerServiceUrl + "/{clientId}";
        return restTemplate.getForObject(url, CustomerResponse.class, customerId);
    }

    @Override
    public CustomerResponse findCustomerByName(String clientName) {
        String url = customerServiceUrl + "/buscar?name={name}";
        return restTemplate.getForObject(url, CustomerResponse.class, clientName);
    }

}
