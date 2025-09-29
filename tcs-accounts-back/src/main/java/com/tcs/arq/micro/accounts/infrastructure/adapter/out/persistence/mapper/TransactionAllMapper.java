package com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.mapper;

import com.tcs.arq.micro.accounts.domain.model.TransactionAll;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.entity.TransactionEntity;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.rest.customer.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionAllMapper {

    TransactionAllMapper INSTANCE = Mappers.getMapper(TransactionAllMapper.class);

    @Mapping(source = "transaction.date", target = "date")
    @Mapping(source = "customer.name", target = "clientName")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "accountType", target = "accountType")
    @Mapping(target = "initialBalance", ignore = true)
    @Mapping(source = "transaction.account.status", target = "status")
    @Mapping(source = "transaction.amount", target = "transactionAmount")
    @Mapping(source = "transaction.balance", target = "availableBalance")
    TransactionAll toModel(TransactionEntity transaction, CustomerResponse customer, Long accountNumber, String accountType);

}