package com.ops.ops.rest.dto.order;

import com.ops.ops.rest.dto.customer.CustomerDto;
import com.ops.ops.rest.dto.offer.OfferDto;
import com.ops.ops.rest.dto.order.OrderStatus;
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

    private CustomerDto customer;

    private OrderStatus status;

    private LocalDateTime creationDate;

    private int quantity;

}