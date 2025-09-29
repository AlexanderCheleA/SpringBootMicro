package com.tcs.arq.micro.accounts.infrastructure.config;

import com.tcs.arq.micro.accounts.domain.port.out.rest.customer.AccountCustomerPort;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.JpaAccountRepositoryImpl;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.JpaTransactionRepositoryImpl;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.repository.JpaAccountRepository;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.persistence.repository.JpaTransactionRepository;
import com.tcs.arq.micro.accounts.infrastructure.adapter.out.rest.customer.AccountCustomerImpl;
import com.tcs.arq.micro.accounts.utils.AccountEndpointsName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
    
    @Bean
    public JpaAccountRepositoryImpl JpaAccountRepositoryImpl(JpaAccountRepository jpaAccountRepository) {
        return new JpaAccountRepositoryImpl(jpaAccountRepository);
    }

    @Bean
    public JpaTransactionRepositoryImpl JpaTransactionRepositoryImpl(
            JpaTransactionRepository jpaTransactionRepository, JpaAccountRepository jpaAccountRepository, AccountCustomerPort accountCustomerPort
    ) {
        return new JpaTransactionRepositoryImpl(jpaTransactionRepository, jpaAccountRepository, accountCustomerPort);
    }

    @Bean
    public AccountCustomerPort accountCustomerPort(
            RestTemplate restTemplate,
            @Value(AccountEndpointsName.ExternalService.CUSTOMER_SERVICE_URL) String customerServiceUrl
    ) {
        return new AccountCustomerImpl(restTemplate, customerServiceUrl);
    }

}

