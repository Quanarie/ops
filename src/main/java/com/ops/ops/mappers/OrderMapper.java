package com.ops.ops.mappers;

import com.ops.ops.persistence.entities.UserEntity;
import com.ops.ops.persistence.entities.OfferEntity;
import com.ops.ops.persistence.entities.OrderEntity;
import com.ops.ops.rest.dto.order.CreateOrderRequest;
import com.ops.ops.rest.dto.order.OrderDto;
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
                .user(UserMapper.toDto(orderEntity.getUser()))
                .build();
    }

    public static OrderEntity toEntity(CreateOrderRequest request,
                                       UserEntity userEntity,
                                       OfferEntity offerEntity) {
        return OrderEntity.builder()
                .status(request.getStatus())
                .quantity(request.getQuantity())
                .offer(offerEntity)
                .creationDate(LocalDateTime.now())
                .user(userEntity)
                .build();
    }
}
