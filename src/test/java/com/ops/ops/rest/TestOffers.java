package com.ops.ops.rest;

import com.ops.ops.persistence.entities.OfferEntity;
import com.ops.ops.rest.dto.offer.requests.CreateOfferRequest;
import com.ops.ops.rest.dto.offer.requests.UpdateOfferRequest;
import com.ops.ops.rest.dto.offer.responces.OfferDto;

import java.util.UUID;

public class TestOffers {

    private static final UUID DEFAULT_UUID = UUID.fromString("52bbd059-f8b0-4a2c-a4d5-e8817d699bb8");
    private static final String DEFAULT_TITLE = "Ice Cream";
    private static final Float DEFAULT_PRICE = 10F;
    private static final String UPDATED_TITLE = "Ice Cream with chocolate";
    private static final Float UPDATED_PRICE = 15F;

    public static final OfferDto OFFER_DTO = createOfferDto();
    public static final OfferDto UPDATED_OFFER_DTO = createUpdatedOfferDto();
    public static final OfferEntity OFFER_ENTITY = createOfferEntity();
    public static final CreateOfferRequest CREATE_OFFER_REQUEST = createCreateOfferRequest();
    public static final UpdateOfferRequest UPDATE_OFFER_REQUEST = createUpdateOfferRequest();

    private static OfferDto createOfferDto() {
        return OfferDto.builder()
                .uuid(DEFAULT_UUID)
                .title(DEFAULT_TITLE)
                .price(DEFAULT_PRICE)
                .build();
    }

    private static OfferDto createUpdatedOfferDto() {
        return OfferDto.builder()
                .uuid(DEFAULT_UUID)
                .title(UPDATED_TITLE)
                .price(UPDATED_PRICE)
                .build();
    }

    private static OfferEntity createOfferEntity() {
        return OfferEntity.builder()
                .id(1L)
                .uuid(DEFAULT_UUID)
                .title(DEFAULT_TITLE)
                .price(DEFAULT_PRICE)
                .build();
    }

    private static CreateOfferRequest createCreateOfferRequest() {
        return CreateOfferRequest.builder()
                .title(DEFAULT_TITLE)
                .price(DEFAULT_PRICE)
                .build();
    }

    private static UpdateOfferRequest createUpdateOfferRequest() {
        return UpdateOfferRequest.builder()
                .title(UPDATED_TITLE)
                .price(UPDATED_PRICE)
                .build();
    }
}