package com.ops.ops.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.ops.TestCustomers;
import com.ops.ops.TestOffers;
import com.ops.ops.persistence.entities.OfferEntity;
import com.ops.ops.persistence.repositories.OfferRepository;
import com.ops.ops.rest.dto.offer.requests.CreateOfferRequest;
import com.ops.ops.rest.dto.offer.requests.UpdateOfferRequest;
import com.ops.ops.rest.dto.offer.responces.OfferDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTest {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @AfterEach
    void cleanup() {
        offerRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = TestCustomers.testUsername, roles = "SELLER")
    void shouldCreateOffer() throws Exception {
        CreateOfferRequest request = TestOffers.CREATE_OFFER_REQUEST;

        String response = mockMvc.perform(post("/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        OfferDto responseOffer = objectMapper.readValue(response, OfferDto.class);
        TestOffers.OFFER_DTO.setUuid(responseOffer.getUuid());
        assertEquals(responseOffer, TestOffers.OFFER_DTO);
    }

    @Test
    @WithMockUser(value = TestCustomers.testUsername)
    void shouldGetOffer() throws Exception {
        offerRepository.save(TestOffers.OFFER_ENTITY);

        String response = mockMvc.perform(get("/offers")
                        .param("uuid", TestOffers.OFFER_ENTITY.getUuid().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        OfferDto responseOffer = objectMapper.readValue(response, OfferDto.class);
        TestOffers.OFFER_DTO.setUuid(responseOffer.getUuid());

        assertEquals(responseOffer, TestOffers.OFFER_DTO);
    }

    @Test
    @WithMockUser(value = TestCustomers.testUsername, roles = "SELLER")
    void shouldUpdateOffer() throws Exception {
        offerRepository.save(TestOffers.OFFER_ENTITY);

        UpdateOfferRequest request = TestOffers.UPDATE_OFFER_REQUEST;

        String response = mockMvc.perform(put("/offers")
                        .param("uuid", TestOffers.OFFER_ENTITY.getUuid().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.readValue(response, OfferDto.class), TestOffers.UPDATED_OFFER_DTO);
    }

    @Test
    @Transactional  // ASK без цього тест не проходиться
    @WithMockUser(value = TestCustomers.testUsername, roles = "SELLER")
    void shouldDeleteOffer() throws Exception {
        OfferEntity saved = offerRepository.save(TestOffers.OFFER_ENTITY);

        mockMvc.perform(delete("/offers")
                        .param("uuid", saved.getUuid().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertFalse(offerRepository.existsById(saved.getId()));
    }
}
