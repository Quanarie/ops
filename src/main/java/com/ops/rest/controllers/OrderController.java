package com.ops.rest.controllers;

import com.ops.rest.dto.order.CreateOrderRequest;
import com.ops.rest.dto.order.UpdateOrderRequest;
import com.ops.rest.dto.order.OrderDto;
import com.ops.services.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public OrderDto createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.create(request);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public OrderDto getOrder(@RequestParam("uuid") @NotNull UUID uuid) {
        return orderService.get(uuid);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_DELIVERER')")
    public OrderDto updateOrder(@RequestParam("uuid") @NotNull UUID uuid,
                                @RequestBody UpdateOrderRequest request) {
        return orderService.update(uuid, request);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public void deleteOrder(@RequestParam("uuid") @NotNull UUID uuid) {
        orderService.delete(uuid);
    }

}
