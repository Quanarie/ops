package com.ops.mappers;

import com.ops.persistence.entities.OfferEntity;
import com.ops.rest.dto.offer.CreateOfferRequest;
import com.ops.rest.dto.offer.OfferDto;

public class OfferMapper {

    public static OfferDto toDto(OfferEntity offer) {
        return OfferDto.builder()
                .uuid(offer.getUuid())
                .price(offer.getPrice())
                .title(offer.getTitle())
                .build();
    }

    public static OfferEntity toEntity(CreateOfferRequest offer) {
        return OfferEntity.builder()
                .price(offer.getPrice())
                .title(offer.getTitle())
                .build();
    }
}
