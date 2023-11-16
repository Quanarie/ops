package com.ops.ops;

import com.ops.ops.persistence.entities.OfferEntity;
import com.ops.ops.rest.dto.offer.requests.CreateOfferRequest;
import com.ops.ops.rest.dto.offer.requests.UpdateOfferRequest;
import com.ops.ops.rest.dto.offer.responces.OfferDto;

import java.util.UUID;

public class TestOffers {

    public static CreateOfferRequest CREATE_OFFER_REQUEST = createCreateOfferRequest();

    public  static OfferDto UPDATED_OFFER_DTO = createUpdatedOfferDto();

    public static UpdateOfferRequest UPDATE_OFFER_REQUEST = createUpdateOfferRequest();

    public static OfferDto OFFER_DTO = createOfferDto();

    public static OfferEntity OFFER_ENTITY = createOfferEntity();

    private static OfferDto createOfferDto() {
        return OfferDto.builder()
                .uuid(UUID.fromString("52bbd059-f8b0-4a2c-a4d5-e8817d699bb8"))
                .title("Ice Cream")
                .price(10F)
                .build();
    }

    private static OfferDto createUpdatedOfferDto() {
        return OfferDto.builder()
                .uuid(UUID.fromString("52bbd059-f8b0-4a2c-a4d5-e8817d699bb8"))
                .title("Ice Cream with chocolate")
                .price(15F)
                .build();
    }

    private static OfferEntity createOfferEntity() {
        return OfferEntity.builder()
                .id(1L)
                .uuid(UUID.fromString("52bbd059-f8b0-4a2c-a4d5-e8817d699bb8"))
                .title("Ice Cream")
                .price(10F)
                .build();
    }

    private static CreateOfferRequest createCreateOfferRequest() {
        return CreateOfferRequest.builder()
                .title("Ice Cream")
                .price(10F)
                .build();
    }

    private static UpdateOfferRequest createUpdateOfferRequest() {
        return UpdateOfferRequest.builder()
                .title("Ice Cream with chocolate")
                .price(15F)
                .build();
    }
}
