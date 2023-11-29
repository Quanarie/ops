package com.ops.services.impl;

import com.ops.mappers.OrderMapper;
import com.ops.persistence.entities.UserEntity;
import com.ops.persistence.entities.OfferEntity;
import com.ops.persistence.entities.OrderEntity;
import com.ops.persistence.repositories.OrderRepository;
import com.ops.rest.dto.order.CreateOrderRequest;
import com.ops.rest.dto.order.UpdateOrderRequest;
import com.ops.rest.dto.order.OrderDto;
import com.ops.services.OfferService;
import com.ops.services.OrderService;
import com.ops.exceptions.ExceptionCodes;
import com.ops.exceptions.NotFoundException;
import com.ops.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;

    private final OfferService offerService;

    private final OrderRepository orderRepository;

    @Override
    public OrderDto create(CreateOrderRequest request) {
        String currentUsername = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        UserEntity user = userService.getByUsernameOrThrow(currentUsername);
        OfferEntity offer = offerService.getByUuidOrThrow(request.getOfferUuid());

        OrderEntity toBeSaved = OrderMapper.toEntity(request, user, offer);

        return OrderMapper.toDto(orderRepository.save(toBeSaved));
    }

    @Override
    public OrderDto get(UUID uuid) {
        OrderEntity entity = orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(
                                "Order with uuid " + uuid + " does not exist",
                                ExceptionCodes.ORDER_NOT_FOUND
                        )
                );

        return OrderMapper.toDto(entity);
    }

    @Override
    public OrderDto update(UUID uuid, UpdateOrderRequest request) {
        OrderEntity entity = orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(
                                "Order with uuid " + uuid + " does not exist",
                                ExceptionCodes.ORDER_NOT_FOUND
                        )
                );

        if (null != request.getStatus())
            entity.setStatus(request.getStatus());

        return OrderMapper.toDto(orderRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        orderRepository.deleteByUuid(uuid);
    }
}
