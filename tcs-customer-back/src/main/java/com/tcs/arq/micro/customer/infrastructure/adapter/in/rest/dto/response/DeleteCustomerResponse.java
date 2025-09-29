package com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCustomerResponse {

    private String message;
    private CustomerResponse customer;
}
