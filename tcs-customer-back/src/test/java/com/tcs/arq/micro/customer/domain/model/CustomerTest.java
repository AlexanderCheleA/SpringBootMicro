package com.tcs.arq.micro.customer.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTest {

    @Test
    void shouldCreateCustomerWithValidData() {
        Customer customer = new Customer(
                1L,
                "securePassword123",
                true
        );

        customer.setName("Juan Perez");
        customer.setGender("Male");
        customer.setAge(30);
        customer.setIdentification("1234567890");
        customer.setAddress("Calle 123");
        customer.setPhone("555-1234");

        assertEquals(1L, customer.getClientId());
        assertEquals("securePassword123", customer.getPassword());
        assertTrue(customer.getStatus());

        assertEquals("Juan Perez", customer.getName());
        assertEquals("Male", customer.getGender());
        assertEquals(30, customer.getAge());
        assertEquals("1234567890", customer.getIdentification());
        assertEquals("Calle 123", customer.getAddress());
        assertEquals("555-1234", customer.getPhone());
    }
}
