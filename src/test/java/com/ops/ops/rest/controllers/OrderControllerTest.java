package com.ops.ops.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ops.ops.rest.TestUsers;
import com.ops.ops.rest.TestOffers;
import com.ops.ops.rest.TestOrders;
import com.ops.ops.persistence.entities.OrderEntity;
import com.ops.ops.persistence.repositories.UserRepository;
import com.ops.ops.persistence.repositories.OfferRepository;
import com.ops.ops.persistence.repositories.OrderRepository;
import com.ops.ops.rest.dto.order.CreateOrderRequest;
import com.ops.ops.rest.dto.order.UpdateOrderRequest;
import com.ops.ops.rest.dto.order.OrderDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
public class OrderControllerTest {

    @Autowired  // TODO
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

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
        userRepository.save(TestUsers.USER_ENTITY);
        offerRepository.save(TestOffers.OFFER_ENTITY);
    }

    @AfterEach
    void cleanup() {
        orderRepository.deleteAll();
        userRepository.deleteAll();
        offerRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = TestUsers.DEFAULT_USERNAME, roles = "BUYER")
    void shouldCreateOrder() throws Exception {
        CreateOrderRequest request = TestOrders.CREATE_ORDER_REQUEST;

        String response = mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        OrderDto responseOrder = objectMapper.readValue(response, OrderDto.class);

        assertNotNull(responseOrder.getUuid());
        assertEquals(TestOrders.ORDER_DTO.getOffer(), responseOrder.getOffer());
        assertEquals(TestOrders.ORDER_DTO.getStatus(), responseOrder.getStatus());
        assertEquals(TestOrders.ORDER_DTO.getQuantity(), responseOrder.getQuantity());
        assertEquals(TestOrders.ORDER_DTO.getUser().getUsername(), responseOrder.getUser().getUsername());
    }

    @Test
    @WithMockUser(value = TestUsers.DEFAULT_USERNAME, roles = "BUYER")
    void shouldGetOrder() throws Exception {
        orderRepository.save(TestOrders.ORDER_ENTITY);

        String response = mockMvc.perform(get("/orders")
                        .param("uuid", String.valueOf(TestOrders.ORDER_ENTITY.getUuid()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        OrderDto responseOrder = objectMapper.readValue(response, OrderDto.class);

        assertNotNull(responseOrder.getUuid());
        assertEquals(TestOrders.ORDER_DTO.getOffer(), responseOrder.getOffer());
        assertEquals(TestOrders.ORDER_DTO.getStatus(), responseOrder.getStatus());
        assertEquals(TestOrders.ORDER_DTO.getQuantity(), responseOrder.getQuantity());
        assertEquals(TestOrders.ORDER_DTO.getUser().getUsername(), responseOrder.getUser().getUsername());
    }

    @Test
    @WithMockUser(value = TestUsers.DEFAULT_USERNAME, roles = "DELIVERER")
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
    @WithMockUser(value = TestUsers.DEFAULT_USERNAME, roles = "BUYER")
    void shouldDeleteOrder() throws Exception {
        OrderEntity saved = orderRepository.save(TestOrders.ORDER_ENTITY);

        mockMvc.perform(delete("/orders")
                        .param("uuid", String.valueOf(TestOrders.ORDER_ENTITY.getUuid()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertFalse(orderRepository.existsById(saved.getId()));
    }
}
