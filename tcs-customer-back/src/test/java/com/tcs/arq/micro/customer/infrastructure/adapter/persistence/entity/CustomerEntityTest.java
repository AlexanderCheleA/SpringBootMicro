package com.tcs.arq.micro.customer.infrastructure.adapter.persistence.entity;

import com.tcs.arq.micro.customer.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerEntityTest {

    @Test
    void shouldCreateCustomerEntityWithAllFields() {
        CustomerEntity customer = new CustomerEntity();

        customer.setPersonId(1L);
        customer.setName("Juan Pérez");
        customer.setGender("M");
        customer.setAge(30);
        customer.setIdentification("1234567890");
        customer.setAddress("Calle Falsa 123");
        customer.setPhone("555-1234");

        customer.setClientId(10L);
        customer.setPassword("securepassword123");
        customer.setStatus(true);

        assertEquals(1L, customer.getPersonId());
        assertEquals("Juan Pérez", customer.getName());
        assertEquals("M", customer.getGender());
        assertEquals(30, customer.getAge());
        assertEquals("1234567890", customer.getIdentification());
        assertEquals("Calle Falsa 123", customer.getAddress());
        assertEquals("555-1234", customer.getPhone());

        assertEquals(10L, customer.getClientId());
        assertEquals("securepassword123", customer.getPassword());
        assertTrue(customer.getStatus());
    }
}
