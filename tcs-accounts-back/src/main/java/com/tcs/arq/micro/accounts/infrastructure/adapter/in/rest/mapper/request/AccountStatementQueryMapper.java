package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.request;

import com.tcs.arq.micro.accounts.domain.dto.AccountStatementQuery;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request.AccountStatementRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountStatementQueryMapper {

    AccountStatementQueryMapper INSTANCE = Mappers.getMapper(AccountStatementQueryMapper.class);

    AccountStatementQuery toQuery(AccountStatementRequest request);

}
