package com.ops.ops.rest.dto.order.requests;

import com.ops.ops.rest.dto.order.OrderStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor  // ASK doesnt work with builder
@AllArgsConstructor
public class UpdateOrderRequest {

    private OrderStatus status;

}
