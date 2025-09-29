package com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.mapper.response;

import com.tcs.arq.micro.customer.domain.model.Customer;
import com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.dto.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerResponseMapper {

    CustomerResponseMapper INSTANCE = Mappers.getMapper(CustomerResponseMapper.class);

    @Mappings({
            @Mapping(target = "clientId", source = "clientId"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "gender", source = "gender"),
            @Mapping(target = "age", source = "age"),
            @Mapping(target = "identification", source = "identification"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "phone", source = "phone"),
            @Mapping(target = "status", source = "status")
    })
    CustomerResponse toDTO(Customer model);

    List<CustomerResponse> toDTOList(List<Customer> modelList);

}
