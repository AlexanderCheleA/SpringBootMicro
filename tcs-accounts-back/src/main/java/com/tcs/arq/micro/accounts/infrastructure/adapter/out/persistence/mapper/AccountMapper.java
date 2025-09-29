package com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.mapper;

import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mappings({
            @Mapping(target = "transactions", ignore = true)
    })
    AccountEntity toEntity(Account account);


    Account toDomain(AccountEntity entity);
}

