package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.request;

import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request.AccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountRequestMapper {

    AccountRequestMapper INSTANCE = Mappers.getMapper(AccountRequestMapper.class);

    @Mappings({
            @Mapping(target = "accountType", source = "accountType"),
            @Mapping(target = "initialBalance", source = "initialBalance"),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "clientId", source = "clientId"),
            @Mapping(target = "accountNumber", ignore = true),
            @Mapping(target = "transactions", ignore = true)
    })
    Account toModel(AccountRequest request);
}
