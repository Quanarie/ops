package com.ops.ops.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.ops.TestCustomers;
import com.ops.ops.mappers.CustomerMapper;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import com.ops.ops.rest.dto.customer.requests.UpdateCustomerDto;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.utils.exceptions.CustomerException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        CustomerDto customerDto = TestCustomers.CUSTOMER_DTO;

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isOk());

        CustomerEntity customerEntity = customerRepository
                .findByUsername(customerDto.getUsername())
                .orElse(null);

        assertNotNull(customerEntity);
        assertEquals(customerDto.getName(), customerEntity.getName());
        assertEquals(customerDto.getPhoneNumber(), customerEntity.getPhoneNumber());
        assertEquals(customerDto.getAddress(), customerEntity.getAddress());

    }

    @Test
    void shouldNotCreateCustomersWithSameUsername() throws Exception {
        customerRepository.save(TestCustomers.CUSTOMER_ENTITY); //same username as CUSTOMER_DTO

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestCustomers.CUSTOMER_DTO)))
                .andExpect(status().isConflict());

    }

    @Test
    void shouldGetCustomer() throws Exception {
        customerRepository.save(TestCustomers.CUSTOMER_ENTITY);

        String response = mockMvc.perform(get("/customers")
                        .param("username", TestCustomers.CUSTOMER_ENTITY.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(response, objectMapper.writeValueAsString(TestCustomers.CUSTOMER_DTO));

    }

    @Test
    void shouldUpdateCustomer() throws Exception {
        customerRepository.save(TestCustomers.CUSTOMER_ENTITY);

        UpdateCustomerDto request = TestCustomers.UPDATE_CUSTOMER_DTO;

        String response = mockMvc.perform(put("/customers")
                        .param("username", TestCustomers.CUSTOMER_ENTITY.getUsername())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // change it bruh
        CustomerDto customerDtoAfterUpdate = CustomerMapper.toDto(customerRepository
                .findByUsername(TestCustomers.CUSTOMER_ENTITY.getUsername()).get());

        assertEquals(response, objectMapper.writeValueAsString(customerDtoAfterUpdate));
    }

    @AfterEach
    void cleanup() {
        customerRepository.deleteAll();
    }
}
