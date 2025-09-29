package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.request;

import com.tcs.arq.micro.accounts.domain.model.Transaction;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request.TransactionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionRequestMapper {

    TransactionRequestMapper INSTANCE = Mappers.getMapper(TransactionRequestMapper.class);

    Transaction toModel(TransactionRequest dto);

}
