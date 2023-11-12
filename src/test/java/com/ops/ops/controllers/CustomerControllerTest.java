package com.ops.ops.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.ops.dto.customer.CustomerDto;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateCustomer() throws Exception {
        CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .name("Peter Parker")
                .phoneNumber("000111222333")
                .address("New York")
                .build();

        mockMvc.perform(post("/customers")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isOk());

        CustomerEntity customerEntity = customerRepository.findById(1L).orElse(null);
        assertNotNull(customerEntity);
        assertEquals("Peter Parker", customerEntity.getName());
        assertEquals("000111222333", customerEntity.getPhoneNumber());
        assertEquals("New York", customerEntity.getAddress());

    }
}
