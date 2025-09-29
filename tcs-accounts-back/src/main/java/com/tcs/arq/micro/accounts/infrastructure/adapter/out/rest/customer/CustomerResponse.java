package com.tcs.arq.micro.accounts.infrastructure.adapter.out.rest.customer;

import lombok.Data;

@Data
public class CustomerResponse {

    private Long clientId;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private Boolean status;

}
