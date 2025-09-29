package com.tcs.arq.micro.customer.infrastructure.adapter.out.persistence.mapper;

import com.tcs.arq.micro.customer.domain.model.Customer;
import com.tcs.arq.micro.customer.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerEntity toEntity(Customer customer);
    Customer toDomain(CustomerEntity entity);

}
