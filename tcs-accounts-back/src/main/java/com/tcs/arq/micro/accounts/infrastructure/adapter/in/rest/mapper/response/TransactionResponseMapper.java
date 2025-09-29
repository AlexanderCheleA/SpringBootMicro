package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.response;


import com.tcs.arq.micro.accounts.domain.model.Transaction;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.request.TransactionRequest;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.response.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionResponseMapper {

    TransactionResponseMapper INSTANCE = Mappers.getMapper(TransactionResponseMapper.class);

    Transaction toModel(TransactionRequest dto);

    TransactionResponse toDTO(Transaction model);

    List<TransactionResponse> toDTOList(List<Transaction> entities);

}
