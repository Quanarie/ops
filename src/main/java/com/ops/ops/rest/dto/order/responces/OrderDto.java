package com.ops.ops.rest.dto.order.responces;

import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.rest.dto.order.OrderStatus;
import com.ops.ops.rest.dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
public class OrderDto {

    private UUID uuid;

    private ProductDto product;

    private CustomerDto customer;

    private int quantity;

    private OrderStatus status;

    private LocalDateTime creationDate;

}