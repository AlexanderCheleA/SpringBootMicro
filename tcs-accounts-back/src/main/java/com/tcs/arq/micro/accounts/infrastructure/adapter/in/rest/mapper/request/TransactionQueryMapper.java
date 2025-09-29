package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.request;

import com.tcs.arq.micro.accounts.domain.dto.TransactionQuery;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request.TransactionQueryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionQueryMapper {

    TransactionQueryMapper INSTANCE = Mappers.getMapper(TransactionQueryMapper.class);

    TransactionQuery toQuery(TransactionQueryRequest request);

}
