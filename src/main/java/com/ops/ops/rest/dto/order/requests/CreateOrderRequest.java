package com.ops.ops.rest.dto.order.requests;

import com.ops.ops.rest.dto.offer.responces.OfferDto;
import com.ops.ops.rest.dto.order.OrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
@Builder
public class CreateOrderRequest {

    private UUID offerUuid;

    private int quantity;

    private OrderStatus status;

}
