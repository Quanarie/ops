package com.ops.ops.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.ops.TestCustomers;
import com.ops.ops.dto.customer.CustomerDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                .name("Hulk")
                .nickname("superHero")
                .phoneNumber("0987654321")
                .address("York New")
                .build();

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isCreated());

        CustomerEntity customerEntity = customerRepository.findByNickname("peter1337228").orElse(null);
        assertNotNull(customerEntity);
        assertEquals("Peter Parker", customerEntity.getName());
        assertEquals("000111222333", customerEntity.getPhoneNumber());
        assertEquals("New York", customerEntity.getAddress());

        customerRepository.deleteAll();

    }

    @Test
    void shouldNotCreateCustomersWithSameNickname() throws Exception {
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

        customerRepository.deleteAll();

    }

    @Test
    void shouldGetCustomer() throws Exception {
        customerRepository.save(TestCustomers.CUSTOMER_ENTITY);

        String response = mockMvc.perform(get("/customers?nickname=superHero")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(response, objectMapper.writeValueAsString(TestCustomers.CUSTOMER_ENTITY));

        customerRepository.deleteAll();

    }

}
