package com.ops.ops.rest.dto.offer.requests;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateOfferRequest {

    private String title;

    private float price;

}
