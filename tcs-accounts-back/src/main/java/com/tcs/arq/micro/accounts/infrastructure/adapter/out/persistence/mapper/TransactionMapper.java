package com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.mapper;

import com.tcs.arq.micro.accounts.domain.model.Transaction;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionEntity toEntity(Transaction transaction);

    Transaction toDomain(TransactionEntity entity);
}

