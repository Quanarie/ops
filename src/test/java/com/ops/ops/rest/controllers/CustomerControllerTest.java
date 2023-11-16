package com.ops.ops.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.ops.rest.TestCustomers;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import com.ops.ops.rest.dto.customer.CreateCustomerRequest;
import com.ops.ops.rest.dto.customer.UpdateCustomerRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
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

    @AfterEach
    void cleanup() {
        customerRepository.deleteAll();
    }

    @Test
    void shouldCreateCustomer() throws Exception {
        CreateCustomerRequest request = TestCustomers.CREATE_CUSTOMER_REQUEST;

        String response = mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(response, objectMapper.writeValueAsString(TestCustomers.CUSTOMER_DTO));
    }

    @Test
    void shouldNotCreateCustomersWithSameUsername() throws Exception {
        customerRepository.save(TestCustomers.CUSTOMER_ENTITY);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestCustomers.CREATE_CUSTOMER_REQUEST)))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(value = TestCustomers.DEFAULT_USERNAME)
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
    @WithMockUser(value = TestCustomers.DEFAULT_USERNAME)
    void shouldNotGetIfNotInDatabase() throws Exception {
        mockMvc.perform(get("/customers")
                        .param("username", TestCustomers.CUSTOMER_ENTITY.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = TestCustomers.DEFAULT_USERNAME)
    void shouldUpdateCustomer() throws Exception {
        customerRepository.save(TestCustomers.CUSTOMER_ENTITY);

        UpdateCustomerRequest request = TestCustomers.UPDATE_CUSTOMER_REQUEST;

        String response = mockMvc.perform(put("/customers")
                        .param("username", TestCustomers.CUSTOMER_ENTITY.getUsername())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(response, objectMapper.writeValueAsString(TestCustomers.UPDATED_CUSTOMER_DTO));
    }

    @Test
    @WithMockUser(value = TestCustomers.DEFAULT_USERNAME)
    void shouldDeleteCustomer() throws Exception {
        CustomerEntity saved = customerRepository.save(TestCustomers.CUSTOMER_ENTITY);

        mockMvc.perform(delete("/customers")
                        .param("username", TestCustomers.CUSTOMER_ENTITY.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertFalse(customerRepository.existsById(saved.getId()));
    }
}
