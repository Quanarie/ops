package com.ops.ops.rest.dto.order;

import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.rest.dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private ProductDto product;

    private CustomerDto customer;

    private int quantity;

    private OrderStatus status;

    private LocalDateTime creationDate;

}