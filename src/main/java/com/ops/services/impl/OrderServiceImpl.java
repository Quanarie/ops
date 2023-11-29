package com.ops.services.impl;

import com.ops.mappers.OrderMapper;
import com.ops.persistence.entities.UserEntity;
import com.ops.persistence.entities.OfferEntity;
import com.ops.persistence.entities.OrderEntity;
import com.ops.persistence.repositories.UserRepository;
import com.ops.persistence.repositories.OfferRepository;
import com.ops.persistence.repositories.OrderRepository;
import com.ops.rest.dto.order.CreateOrderRequest;
import com.ops.rest.dto.order.UpdateOrderRequest;
import com.ops.rest.dto.order.OrderDto;
import com.ops.services.OrderService;
import com.ops.exceptions.ExceptionCodes;
import com.ops.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final OfferRepository offerRepository;

    @Override
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public OrderDto create(CreateOrderRequest request) {
        String currentUsername = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        UserEntity user = userRepository
                .findByUsername(currentUsername)
                .orElseThrow(() -> new NotFoundException(
                                "Authenticated user " + currentUsername + " not found",
                                ExceptionCodes.USER_NOT_FOUND
                        )
                );

        OfferEntity offer = offerRepository
                .findByUuid(request.getOfferUuid())
                .orElseThrow(() -> new NotFoundException(
                                "Offer with uuid " + request.getOfferUuid() + " not found",
                                ExceptionCodes.OFFER_NOT_FOUND
                        )
                );

        OrderEntity toBeSaved = OrderMapper.toEntity(request, user, offer);

        return OrderMapper.toDto(orderRepository.save(toBeSaved));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_BUYER')")
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
    @PreAuthorize("hasRole('ROLE_DELIVERER')")
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
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public void delete(UUID uuid) {
        orderRepository.deleteByUuid(uuid);
    }
}
