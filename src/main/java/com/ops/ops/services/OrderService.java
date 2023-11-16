package com.ops.ops.services;

import com.ops.ops.rest.dto.order.requests.CreateOrderRequest;
import com.ops.ops.rest.dto.order.requests.UpdateOrderRequest;
import com.ops.ops.rest.dto.order.responces.OrderDto;

import java.util.UUID;

public interface OrderService {

    OrderDto create(CreateOrderRequest request);

    OrderDto get(UUID uuid);

    OrderDto update(UUID uuid, UpdateOrderRequest request);

    void delete(UUID uuid);
}
