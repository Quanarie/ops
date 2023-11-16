package com.ops.ops.rest.dto.order;

import com.ops.ops.rest.dto.order.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateOrderRequest {

    private UUID offerUuid;

    private OrderStatus status;

    private int quantity;

}
