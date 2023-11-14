package com.ops.ops.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.ops.persistence.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateOrder() throws Exception {

    }

    @Test
    void shouldGetOrder() throws Exception {

    }

    @Test
    void shouldTrackOrder() throws Exception {

    }

    @Test
    void shouldUpdateOrder() throws Exception {

    }

    @Test
    void shouldGetOrderHistory() throws Exception {

    }

    @Test
    void shouldDeleteOrder() throws Exception {

    }
}
