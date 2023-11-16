package com.ops.ops.rest.dto.order.requests;

import com.ops.ops.rest.dto.order.OrderStatus;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderRequest {

    private OrderStatus status;

}
