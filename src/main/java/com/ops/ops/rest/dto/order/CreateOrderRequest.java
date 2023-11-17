package com.ops.ops.rest.dto.order;

import com.ops.ops.rest.dto.order.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateOrderRequest {

    @NotNull
    private UUID offerUuid;

    @NotNull
    private OrderStatus status;

    @NotNull
    private Integer quantity;

}
