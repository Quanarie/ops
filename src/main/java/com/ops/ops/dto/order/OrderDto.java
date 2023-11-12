package com.ops.ops.dto.order;

import com.ops.ops.dto.customer.CustomerDto;
import com.ops.ops.dto.product.ProductDto;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.entities.ProductEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

@Builder
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private ProductDto product;

    private CustomerDto customer;

    private int quantity;

    private OrderStatus status;

    private Date creationDate;

}