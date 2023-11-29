package com.ops.services;

import com.ops.rest.dto.order.CreateOrderRequest;
import com.ops.rest.dto.order.UpdateOrderRequest;
import com.ops.rest.dto.order.OrderDto;

import java.util.UUID;

public interface OrderService {

    OrderDto create(CreateOrderRequest request);

    OrderDto get(UUID uuid);

    OrderDto update(UUID uuid, UpdateOrderRequest request);

    void delete(UUID uuid);
}
