package com.ops.ops.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.ops.TestCustomers;
import com.ops.ops.mappers.CustomerMapper;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import com.ops.ops.rest.dto.customer.requests.CreateCustomerRequest;
import com.ops.ops.rest.dto.customer.requests.UpdateCustomerRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
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
        CreateCustomerRequest request = TestCustomers.CREATE_CUSTOMER_REQUEST;

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        CustomerEntity customerEntity = customerRepository
                .findByUsername(request.getUsername())
                .orElse(null);

        assertNotNull(customerEntity);
        assertEquals(request.getName(), customerEntity.getName());
        assertEquals(request.getPhoneNumber(), customerEntity.getPhoneNumber());
        assertEquals(request.getAddress(), customerEntity.getAddress());
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
    @WithMockUser(value = TestCustomers.testUsername)
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
    @WithMockUser(value = TestCustomers.testUsername)
    void shouldNotGetIfUsernameIsNotInDatabase() throws Exception {
        mockMvc.perform(get("/customers")
                        .param("username", TestCustomers.CUSTOMER_ENTITY.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = TestCustomers.testUsername)
    void shouldUpdateCustomer() throws Exception {
        CustomerEntity savedCustomerEntity = customerRepository.save(TestCustomers.CUSTOMER_ENTITY);

        UpdateCustomerRequest request = TestCustomers.UPDATE_CUSTOMER_REQUEST;

        String response = mockMvc.perform(put("/customers")
                        .param("username", TestCustomers.CUSTOMER_ENTITY.getUsername())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(response, objectMapper.writeValueAsString(CustomerMapper.toDto(savedCustomerEntity)));
    }
}
