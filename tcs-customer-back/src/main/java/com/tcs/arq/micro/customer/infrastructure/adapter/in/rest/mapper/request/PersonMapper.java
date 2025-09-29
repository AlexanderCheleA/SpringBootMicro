package com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.mapper.request;

import com.tcs.arq.micro.customer.domain.model.Person;
import com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.dto.request.CustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "personId", ignore = true)
    Person toModel(CustomerRequest dto);
}
