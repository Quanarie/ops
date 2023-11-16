package com.ops.ops.rest.dto.offer.responces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

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
