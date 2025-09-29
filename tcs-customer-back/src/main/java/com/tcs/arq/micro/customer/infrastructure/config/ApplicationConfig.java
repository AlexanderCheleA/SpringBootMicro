package com.tcs.arq.micro.customer.infrastructure.config;

import com.tcs.arq.micro.customer.infrastructure.adapter.out.persistence.JpaCustomerRepositoryImpl;
import com.tcs.arq.micro.customer.infrastructure.adapter.out.persistence.repository.JpaCustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    
    @Bean
    public JpaCustomerRepositoryImpl JpaCustomerRepositoryImpl(JpaCustomerRepository jpaCustomerRepository) {
        return new JpaCustomerRepositoryImpl(jpaCustomerRepository);
    }

}

