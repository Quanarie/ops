package com.ops.ops.rest.dto.offer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class CreateOfferRequest {

    @NotBlank
    private String title;

    @NotNull
    private BigDecimal price;

}
