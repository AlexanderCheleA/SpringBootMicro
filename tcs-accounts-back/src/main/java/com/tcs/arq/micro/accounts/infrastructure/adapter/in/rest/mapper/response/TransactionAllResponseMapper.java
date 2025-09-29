package com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.mapper.response;

import com.tcs.arq.micro.accounts.domain.model.TransactionAll;
import com.tcs.arq.micro.accounts.infrastructure.adapter.in.rest.dto.response.TransactionAllResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionAllResponseMapper {

    TransactionAllResponseMapper INSTANCE = Mappers.getMapper(TransactionAllResponseMapper.class);

    TransactionAllResponse toDTO(TransactionAll entity);

    List<TransactionAllResponse> toDTOList(List<TransactionAll> entities);
}

