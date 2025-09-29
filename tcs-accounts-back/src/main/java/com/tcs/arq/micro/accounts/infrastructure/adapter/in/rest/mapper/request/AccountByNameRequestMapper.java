package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.request;

import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request.AccountByNameRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountByNameRequestMapper {

    AccountByNameRequestMapper INSTANCE = Mappers.getMapper(AccountByNameRequestMapper.class);

    @Mappings({
            @Mapping(target = "accountType", source = "accountType"),
            @Mapping(target = "initialBalance", source = "initialBalance"),
            @Mapping(target = "clientName", source = "clientName"),
            @Mapping(target = "status", constant = "true"),
            @Mapping(target = "accountNumber", ignore = true),
            @Mapping(target = "clientId", ignore = true),
            @Mapping(target = "transactions", ignore = true)
    })
    Account toModel(AccountByNameRequest request);
}
