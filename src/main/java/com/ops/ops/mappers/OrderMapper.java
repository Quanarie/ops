package com.ops.ops.mappers;

import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.entities.OfferEntity;
import com.ops.ops.persistence.entities.OrderEntity;
import com.ops.ops.rest.dto.order.requests.CreateOrderRequest;
import com.ops.ops.rest.dto.order.responces.OrderDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {

    public static OrderDto toDto(OrderEntity orderEntity) {
        return OrderDto.builder()
                .uuid(orderEntity.getUuid())
                .quantity(orderEntity.getQuantity())
                .status(orderEntity.getStatus())
                .offer(OfferMapper.toDto(orderEntity.getOffer()))
                .creationDate(orderEntity.getCreationDate())
                .customer(CustomerMapper.toDto(orderEntity.getCustomer()))
                .build();
    }

    public static OrderEntity toEntity(CreateOrderRequest request,
                                       CustomerEntity customerEntity,
                                       OfferEntity offerEntity) {
        return OrderEntity.builder()
                .status(request.getStatus())
                .quantity(request.getQuantity())
                .offer(offerEntity)
                .creationDate(LocalDateTime.now())
                .customer(customerEntity)
                .build();
    }
}
