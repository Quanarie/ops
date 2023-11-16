package com.ops.ops.rest;

import com.ops.ops.persistence.entities.OrderEntity;
import com.ops.ops.rest.dto.order.OrderStatus;
import com.ops.ops.rest.dto.order.CreateOrderRequest;
import com.ops.ops.rest.dto.order.UpdateOrderRequest;
import com.ops.ops.rest.dto.order.OrderDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestOrders {

    private static final UUID DEFAULT_UUID = UUID.fromString("623cf0c1-7dd9-40f5-815a-d5a60541c6ea");
    private static final int DEFAULT_QUANTITY = 1;
    private static final OrderStatus DEFAULT_STATUS = OrderStatus.PROCESSING;
    private static final OrderStatus UPDATED_STATUS = OrderStatus.DELIVERED;

    public static final CreateOrderRequest CREATE_ORDER_REQUEST = createCreateOrderRequest();
    public static final UpdateOrderRequest UPDATE_ORDER_REQUEST = createUpdateOrderRequest();
    public static final OrderDto ORDER_DTO = createOrderDto();
    public static final OrderEntity ORDER_ENTITY = createOrderEntity();

    private static OrderDto createOrderDto() {
        return OrderDto.builder()
                .uuid(DEFAULT_UUID)
                .creationDate(LocalDateTime.now())
                .customer(TestCustomers.CUSTOMER_DTO)
                .offer(TestOffers.OFFER_DTO)
                .quantity(DEFAULT_QUANTITY)
                .status(DEFAULT_STATUS)
                .build();
    }

    private static OrderEntity createOrderEntity() {
        return OrderEntity.builder()
                .uuid(DEFAULT_UUID)
                .creationDate(LocalDateTime.now())
                .customer(TestCustomers.CUSTOMER_ENTITY)
                .offer(TestOffers.OFFER_ENTITY)
                .quantity(DEFAULT_QUANTITY)
                .status(DEFAULT_STATUS)
                .build();
    }

    private static CreateOrderRequest createCreateOrderRequest() {
        return CreateOrderRequest.builder()
                .offerUuid(TestOffers.OFFER_ENTITY.getUuid())
                .status(DEFAULT_STATUS)
                .quantity(DEFAULT_QUANTITY)
                .build();
    }

    private static UpdateOrderRequest createUpdateOrderRequest() {
        return UpdateOrderRequest.builder()
                .status(UPDATED_STATUS)
                .build();
    }
}