package com.ops.ops;

import com.ops.ops.persistence.entities.OrderEntity;
import com.ops.ops.rest.dto.order.OrderStatus;
import com.ops.ops.rest.dto.order.requests.CreateOrderRequest;
import com.ops.ops.rest.dto.order.requests.UpdateOrderRequest;
import com.ops.ops.rest.dto.order.responces.OrderDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestOrders {

    public static CreateOrderRequest CREATE_ORDER_REQUEST = createCreateOrderRequest();

    public static UpdateOrderRequest UPDATE_ORDER_REQUEST = createUpdateOrderRequest();

    public static OrderDto ORDER_DTO = createOrderDto();

    public static OrderEntity ORDER_ENTITY = createOrderEntity();

    private static final UUID testUuid = UUID.fromString("623cf0c1-7dd9-40f5-815a-d5a60541c6ea");

    private static CreateOrderRequest createCreateOrderRequest() {
        return CreateOrderRequest.builder()
                .offerUuid(TestOffers.OFFER_ENTITY.getUuid())
                .status(OrderStatus.PROCESSING)
                .quantity(1)
                .build();
    }

    private static OrderDto createOrderDto() {
        return OrderDto.builder()
                .uuid(testUuid)
                .creationDate(LocalDateTime.now())
                .customer(TestCustomers.CUSTOMER_DTO)
                .offer(TestOffers.OFFER_DTO)
                .quantity(1)
                .status(OrderStatus.PROCESSING)
                .build();
    }

    private static OrderEntity createOrderEntity() {
        return OrderEntity.builder()
                .uuid(testUuid)
                .creationDate(LocalDateTime.now())
                .customer(TestCustomers.CUSTOMER_ENTITY)
                .offer(TestOffers.OFFER_ENTITY)
                .quantity(1)
                .status(OrderStatus.PROCESSING)
                .build();
    }

    private static UpdateOrderRequest createUpdateOrderRequest() {
        return UpdateOrderRequest.builder()
                .status(OrderStatus.DELIVERED)
                .build();
    }
}
