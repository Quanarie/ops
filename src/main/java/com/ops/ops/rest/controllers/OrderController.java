package com.ops.ops.rest.controllers;

import com.ops.ops.rest.dto.order.CreateOrderRequest;
import com.ops.ops.rest.dto.order.UpdateOrderRequest;
import com.ops.ops.rest.dto.order.OrderDto;
import com.ops.ops.services.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderDto createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.create(request);
    }

    @GetMapping
    public OrderDto getOrder(@RequestParam("uuid") @NotNull UUID uuid) {
        return orderService.get(uuid);
    }

    @PutMapping
    public OrderDto updateOrder(@RequestParam("uuid") @NotNull UUID uuid,
                                @RequestBody UpdateOrderRequest request) {
        return orderService.update(uuid, request);
    }

    @DeleteMapping
    public void deleteOrder(@RequestParam("uuid") @NotNull UUID uuid) {
        orderService.delete(uuid);
    }

}
