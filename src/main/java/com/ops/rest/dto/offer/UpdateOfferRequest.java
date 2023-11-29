package com.ops.rest.dto.offer;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class UpdateOfferRequest {

    private String title;

    private BigDecimal price;

}
