package com.ops.ops.rest.dto.order.responces;

import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.rest.dto.offer.responces.OfferDto;
import com.ops.ops.rest.dto.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private UUID uuid;

    private OfferDto offer;

    private CustomerDto customer;

    private int quantity;

    private OrderStatus status;

    private LocalDateTime creationDate;

}