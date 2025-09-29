package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.response;

import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountResponseMapper {

    AccountResponseMapper INSTANCE = Mappers.getMapper(AccountResponseMapper.class);

    @Mappings({
            @Mapping(target = "accountNumber", source = "accountNumber"),
            @Mapping(target = "accountType", source = "accountType"),
            @Mapping(target = "initialBalance", source = "initialBalance"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "clientId", source = "clientId")
    })
    AccountResponse toDTO(Account model);

    List<AccountResponse> toDTOList(List<Account> modelList);


}
