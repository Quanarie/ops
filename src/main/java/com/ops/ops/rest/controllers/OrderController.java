package com.ops.ops.rest.controllers;

import com.ops.ops.rest.dto.order.requests.CreateOrderRequest;
import com.ops.ops.rest.dto.order.requests.UpdateOrderRequest;
import com.ops.ops.rest.dto.order.responces.OrderDto;
import com.ops.ops.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(path = "/orders")
    public OrderDto createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.create(request);
    }

    @GetMapping(path = "/orders")
    public OrderDto getOrder(@RequestParam("uuid") UUID uuid) {
        return orderService.get(uuid);
    }

    @PutMapping(path = "/orders")
    public OrderDto updateOrder(@RequestParam("uuid") UUID uuid,
                                   @RequestBody UpdateOrderRequest request) {
        return orderService.update(uuid, request);
    }

    @DeleteMapping(path = "/orders")
    public void deleteOrder(@RequestParam("uuid") UUID uuid) {
        orderService.delete(uuid);
    }

}
