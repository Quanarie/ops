package com.ops.ops.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ops.ops.TestCustomers;
import com.ops.ops.TestOffers;
import com.ops.ops.TestOrders;
import com.ops.ops.mappers.OrderMapper;
import com.ops.ops.persistence.entities.OfferEntity;
import com.ops.ops.persistence.entities.OrderEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import com.ops.ops.persistence.repositories.OfferRepository;
import com.ops.ops.persistence.repositories.OrderRepository;
import com.ops.ops.rest.dto.offer.responces.OfferDto;
import com.ops.ops.rest.dto.order.requests.CreateOrderRequest;
import com.ops.ops.rest.dto.order.requests.UpdateOrderRequest;
import com.ops.ops.rest.dto.order.responces.OrderDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired  // ASK
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    void writeTestUserToDatabase() {
        customerRepository.save(TestCustomers.CUSTOMER_ENTITY);
        OfferEntity offerEntity = offerRepository.save(TestOffers.OFFER_ENTITY);

        TestOrders.CREATE_ORDER_REQUEST.setOfferUuid(offerEntity.getUuid());
    }

    @AfterEach
    void cleanup() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();
        offerRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = TestCustomers.testUsername)
    void shouldCreateOrder() throws Exception {
        CreateOrderRequest request = TestOrders.CREATE_ORDER_REQUEST;

        String response = mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        OrderDto responseOrder = objectMapper.readValue(response, OrderDto.class);

        assertEquals(TestOrders.ORDER_DTO.getOffer().getUuid(), responseOrder.getOffer().getUuid());
        assertEquals(TestOrders.ORDER_DTO.getStatus(), responseOrder.getStatus());
        assertEquals(TestOrders.ORDER_DTO.getCustomer().getUsername(), responseOrder.getCustomer().getUsername());
    }

    @Test
    @WithMockUser(value = TestCustomers.testUsername)
    void shouldGetOrder() throws Exception {
        orderRepository.save(TestOrders.ORDER_ENTITY);

        String response = mockMvc.perform(get("/orders")
                        .param("uuid", String.valueOf(TestOrders.ORDER_ENTITY.getUuid()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        OrderDto responseOrder = objectMapper.readValue(response, OrderDto.class);

        assertEquals(TestOrders.ORDER_ENTITY.getOffer().getUuid(), responseOrder.getOffer().getUuid());
        assertEquals(TestOrders.ORDER_ENTITY.getStatus(), responseOrder.getStatus());
        assertEquals(TestOrders.ORDER_ENTITY.getCustomer().getUsername(), responseOrder.getCustomer().getUsername());
    }

    @Test
    @WithMockUser(value = TestCustomers.testUsername)
    void shouldUpdateOrder() throws Exception {
        orderRepository.save(TestOrders.ORDER_ENTITY);

        UpdateOrderRequest request = TestOrders.UPDATE_ORDER_REQUEST;

        String response = mockMvc.perform(put("/orders")
                        .param("uuid", String.valueOf(TestOrders.ORDER_ENTITY.getUuid()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        OrderDto responseOrder = objectMapper.readValue(response, OrderDto.class);

        assertEquals(TestOrders.UPDATE_ORDER_REQUEST.getStatus(), responseOrder.getStatus());
    }

    @Test
    @WithMockUser(value = TestCustomers.testUsername)
    void shouldDeleteOrder() throws Exception {
        OrderEntity savedOrderEntity = orderRepository.save(TestOrders.ORDER_ENTITY);

        mockMvc.perform(delete("/orders")
                        .param("uuid", String.valueOf(TestOrders.ORDER_ENTITY.getUuid()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertFalse(orderRepository.existsById(savedOrderEntity.getId()));
    }
}
