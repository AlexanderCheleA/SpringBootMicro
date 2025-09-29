package com.tcs.arq.micro.customer.application.usecases;

import com.tcs.arq.micro.customer.domain.exception.CustomerAlreadyExistsException;
import com.tcs.arq.micro.customer.domain.model.Customer;
import com.tcs.arq.micro.customer.domain.port.out.persistence.repository.CustomerRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateCustomerUseCaseImplTest {

    @Mock
    private CustomerRepositoryPort customerRepositoryPort;

    @InjectMocks
    private CreateCustomerUseCaseImpl createCustomerUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenCustomerDoesNotExist_thenSaveNewCustomer() {
        Customer newCustomer = createCustomer("12345", true);

        when(customerRepositoryPort.findByIdentification(newCustomer.getIdentification()))
                .thenReturn(Optional.empty());
        when(customerRepositoryPort.save(newCustomer)).thenReturn(newCustomer);

        Customer saved = createCustomerUseCase.createCustomer(newCustomer);

        assertNotNull(saved);
        verify(customerRepositoryPort).save(newCustomer);
    }

    @Test
    void whenCustomerExistsAndIsActive_thenThrowException() {
        Customer existingCustomer = createCustomer("12345", true);

        when(customerRepositoryPort.findByIdentification(existingCustomer.getIdentification()))
                .thenReturn(Optional.of(existingCustomer));

        Customer newCustomer = createCustomer("12345", true);

        CustomerAlreadyExistsException exception = assertThrows(
                CustomerAlreadyExistsException.class,
                () -> createCustomerUseCase.createCustomer(newCustomer)
        );

        assertTrue(exception.getMessage().contains(existingCustomer.getIdentification()));
        verify(customerRepositoryPort, never()).save(any());
    }

    @Test
    void whenCustomerExistsAndIsInactive_thenUpdateAndSave() {
        Customer existingCustomer = createCustomer("12345", false);

        Customer updatedCustomer = createCustomer("12345", true);
        updatedCustomer.setName("Nuevo Nombre");

        when(customerRepositoryPort.findByIdentification(existingCustomer.getIdentification()))
                .thenReturn(Optional.of(existingCustomer));
        when(customerRepositoryPort.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Customer result = createCustomerUseCase.createCustomer(updatedCustomer);

        assertNotNull(result);
        assertEquals("Nuevo Nombre", result.getName());
        assertTrue(result.getStatus());
        verify(customerRepositoryPort).save(existingCustomer);
    }

    // Helper method to create customer objects
    private Customer createCustomer(String identification, boolean status) {
        Customer customer = new Customer();
        customer.setIdentification(identification);
        customer.setStatus(status);
        customer.setName("Test Name");
        customer.setGender("M");
        customer.setAge(30);
        customer.setAddress("Test Address");
        customer.setPhone("123456789");
        customer.setPassword("password");
        return customer;
    }
}
