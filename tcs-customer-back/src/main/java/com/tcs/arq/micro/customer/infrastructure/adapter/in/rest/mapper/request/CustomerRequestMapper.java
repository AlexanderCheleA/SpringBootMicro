package com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.mapper.request;

import com.tcs.arq.micro.customer.domain.model.Customer;
import com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.dto.request.CustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PersonMapper.class)
public interface CustomerRequestMapper {

    CustomerRequestMapper INSTANCE = Mappers.getMapper(CustomerRequestMapper.class);

    @Mappings({
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "clientId", ignore = true)
    })
    Customer toModel(CustomerRequest dto);

}
