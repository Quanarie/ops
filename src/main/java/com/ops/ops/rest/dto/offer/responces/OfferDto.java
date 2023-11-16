package com.ops.ops.rest.dto.offer.responces;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {

    private UUID uuid;

    private String title;

    private Float price;

}
