package com.ops.rest.dto.offer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {

    private UUID uuid;

    private String title;

    private BigDecimal price;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        OfferDto yourObject = (OfferDto)obj;

        return Objects.equals(uuid, yourObject.uuid) &&
                Objects.equals(title, yourObject.title) &&
                Objects.equals(price.stripTrailingZeros(), yourObject.price.stripTrailingZeros());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, title, price.stripTrailingZeros());
    }
}