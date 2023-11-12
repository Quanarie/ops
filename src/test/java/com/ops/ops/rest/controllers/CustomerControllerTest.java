package com.ops.ops.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.ops.TestCustomers;
import com.ops.ops.dto.customer.CustomerDto;
import com.ops.ops.dto.customer.UpdateCustomerRequest;
import com.ops.ops.mappers.CustomerMapper;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
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
        customerRepository.deleteAll();

        CustomerDto customerDto = CustomerDto.builder()
                .name("Hulk")
                .nickname("superHero")
                .phoneNumber("0987654321")
                .address("York New")
                .build();

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isCreated());

        CustomerEntity customerEntity = customerRepository
                .findByNickname(customerDto.getNickname())
                .orElse(null);

        assertNotNull(customerEntity);
        assertEquals(customerDto.getName(), customerEntity.getName());
        assertEquals(customerDto.getPhoneNumber(), customerEntity.getPhoneNumber());
        assertEquals(customerDto.getAddress(), customerEntity.getAddress());

    }

    @Test
    void shouldNotCreateCustomersWithSameNickname() throws Exception {
        customerRepository.deleteAll();

        CustomerDto customerDto = CustomerDto.builder()
                .name("Hulk")
                .nickname("superHero")
                .phoneNumber("0987654321")
                .address("York New")
                .build();

        customerRepository.save(TestCustomers.CUSTOMER_ENTITY); //same nickName

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isConflict());

    }

    @Test
    void shouldGetCustomer() throws Exception {
        customerRepository.deleteAll();

        customerRepository.save(TestCustomers.CUSTOMER_ENTITY);

        String response = mockMvc.perform(get("/customers")
                        .param("nickname", "superHero")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(response, objectMapper.writeValueAsString(TestCustomers.CUSTOMER_ENTITY));

    }

    @Test
    void shouldUpdateCustomer() throws Exception {
        customerRepository.deleteAll();

        customerRepository.save(TestCustomers.CUSTOMER_ENTITY);

        UpdateCustomerRequest request = UpdateCustomerRequest.builder()
                .name("Hulk")
                .phoneNumber("0987654321")
                .address("York New")
                .build();

        String response = mockMvc.perform(put("/customers")
                        .param("nickname", TestCustomers.CUSTOMER_ENTITY.getNickname())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        CustomerDto customerDtoAfterUpdate = CustomerMapper.entityToDto(
                CustomerMapper.updateRequestToEntity(request));
        customerDtoAfterUpdate.setNickname(TestCustomers.CUSTOMER_ENTITY.getNickname());

        assertEquals(response, objectMapper.writeValueAsString(customerDtoAfterUpdate));
    }
}
