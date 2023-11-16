package com.ops.ops.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.ops.rest.TestUsers;
import com.ops.ops.rest.TestOffers;
import com.ops.ops.persistence.entities.OfferEntity;
import com.ops.ops.persistence.repositories.OfferRepository;
import com.ops.ops.rest.dto.offer.CreateOfferRequest;
import com.ops.ops.rest.dto.offer.UpdateOfferRequest;
import com.ops.ops.rest.dto.offer.OfferDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
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
    @WithMockUser(value = TestUsers.DEFAULT_USERNAME, roles = "SELLER")
    void shouldCreateOffer() throws Exception {
        CreateOfferRequest request = TestOffers.CREATE_OFFER_REQUEST;

        String response = mockMvc.perform(post("/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        OfferDto responseOffer = objectMapper.readValue(response, OfferDto.class);  // TODO

        assertEquals(responseOffer.getTitle(), TestOffers.OFFER_DTO.getTitle());
        assertEquals(responseOffer.getPrice(), TestOffers.OFFER_DTO.getPrice());
    }

    @Test
    @WithMockUser(value = TestUsers.DEFAULT_USERNAME)
    void shouldGetOffer() throws Exception {
        offerRepository.save(TestOffers.OFFER_ENTITY);

        String response = mockMvc.perform(get("/offers")
                        .param("uuid", TestOffers.OFFER_ENTITY.getUuid().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        OfferDto responseOffer = objectMapper.readValue(response, OfferDto.class);

        assertNotNull(responseOffer.getUuid());
        assertEquals(responseOffer.getTitle(), TestOffers.OFFER_DTO.getTitle());
        assertEquals(0, responseOffer.getPrice().compareTo(TestOffers.OFFER_DTO.getPrice()));
    }

    @Test
    @WithMockUser(value = TestUsers.DEFAULT_USERNAME, roles = "SELLER")
    void shouldUpdateOffer() throws Exception {
        offerRepository.save(TestOffers.OFFER_ENTITY);

        UpdateOfferRequest request = TestOffers.UPDATE_OFFER_REQUEST;

        String response = mockMvc.perform(put("/offers")
                        .param("uuid", TestOffers.OFFER_ENTITY.getUuid().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        OfferDto responseOffer = objectMapper.readValue(response, OfferDto.class);

        assertNotNull(responseOffer.getUuid());
        assertEquals(TestOffers.UPDATED_OFFER_DTO.getTitle(), responseOffer.getTitle());
        assertEquals(0, responseOffer.getPrice().compareTo(TestOffers.UPDATED_OFFER_DTO.getPrice()));
    }

    @Test
    @Transactional  // TODO без цього тест не проходиться
    @WithMockUser(value = TestUsers.DEFAULT_USERNAME, roles = "SELLER")
    void shouldDeleteOffer() throws Exception {
        OfferEntity saved = offerRepository.save(TestOffers.OFFER_ENTITY);

        mockMvc.perform(delete("/offers")
                        .param("uuid", saved.getUuid().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertFalse(offerRepository.existsById(saved.getId()));
    }
}
