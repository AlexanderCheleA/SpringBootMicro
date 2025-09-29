package com.tcs.arq.micro.accounts.application.usecases;

import com.tcs.arq.micro.accounts.domain.exception.CustomerNotFoundException;
import com.tcs.arq.micro.accounts.domain.exception.InactiveCustomerException;
import com.tcs.arq.micro.accounts.domain.exception.MissingClientReferenceException;
import com.tcs.arq.micro.accounts.domain.model.Account;
import com.tcs.arq.micro.accounts.domain.port.in.usecase.CreateAccountUseCase;
import com.tcs.arq.micro.accounts.domain.port.out.persistence.repository.AccountRepositoryPort;
import com.tcs.arq.micro.accounts.domain.port.out.rest.customer.AccountCustomerPort;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.rest.customer.CustomerResponse;
import com.tcs.arq.micro.accounts.utils.AccountMessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;
    private final AccountCustomerPort accountCustomerPort;

    @Transactional
    @Override
    public Account createAccount(Account account) {
        if (account.getClientId() == null) {
            if (account.getClientName() == null || account.getClientName().isBlank()) {
                throw new MissingClientReferenceException(AccountMessagesUtil.Message.CLIENT_ID_OR_NAME_REQUIRED);
            }

            CustomerResponse customerData = accountCustomerPort.findCustomerByName(account.getClientName());

            if (customerData == null || customerData.getClientId() == null) {
                throw new CustomerNotFoundException(AccountMessagesUtil.Message.CUSTOMER_NOT_FOUND);
            }

            if (Boolean.FALSE.equals(customerData.getStatus())) {
                throw new InactiveCustomerException(AccountMessagesUtil.Message.CUSTOMER_INACTIVE);
            }

            account.setClientId(customerData.getClientId());
        }

        return accountRepositoryPort.save(account);
    }
}
