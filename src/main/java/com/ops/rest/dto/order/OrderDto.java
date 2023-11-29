package com.ops.rest.dto.order;

import com.ops.rest.dto.user.UserDto;
import com.ops.rest.dto.offer.OfferDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private UUID uuid;

    private OfferDto offer;

    private UserDto user;

    private OrderStatus status;

    private LocalDateTime creationDate;

    private Integer quantity;

}