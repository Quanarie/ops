package com.ops.ops.services.impl;

import com.ops.ops.mappers.OrderMapper;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.entities.OfferEntity;
import com.ops.ops.persistence.entities.OrderEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import com.ops.ops.persistence.repositories.OfferRepository;
import com.ops.ops.persistence.repositories.OrderRepository;
import com.ops.ops.rest.dto.order.requests.CreateOrderRequest;
import com.ops.ops.rest.dto.order.requests.UpdateOrderRequest;
import com.ops.ops.rest.dto.order.responces.OrderDto;
import com.ops.ops.services.OrderService;
import com.ops.ops.exceptions.ExceptionCodes;
import com.ops.ops.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;

    private final OfferRepository offerRepository;

    @Override
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public OrderDto create(CreateOrderRequest request) {
        String currentUsername = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        CustomerEntity customer = customerRepository
                .findByUsername(currentUsername)
                .orElseThrow(() -> new NotFoundException(
                                "Authenticated user " + currentUsername + " not found",
                                ExceptionCodes.CUSTOMER_NOT_FOUND
                        )
                );

        OfferEntity offer = offerRepository
                .findByUuid(request.getOfferUuid())
                .orElseThrow(() -> new NotFoundException(
                                "Offer with uuid " + request.getOfferUuid() + " not found",
                                ExceptionCodes.OFFER_NOT_FOUND
                        )
                );

        OrderEntity toBeSaved = OrderMapper.toEntity(request, customer, offer);

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
