package com.ops.ops.services.impl;

import com.ops.ops.mappers.OrderMapper;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.entities.OfferEntity;
import com.ops.ops.persistence.entities.OrderEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import com.ops.ops.persistence.repositories.OfferRepository;
import com.ops.ops.persistence.repositories.OrderRepository;
import com.ops.ops.rest.dto.order.OrderStatus;
import com.ops.ops.rest.dto.order.requests.CreateOrderRequest;
import com.ops.ops.rest.dto.order.requests.UpdateOrderRequest;
import com.ops.ops.rest.dto.order.responces.OrderDto;
import com.ops.ops.services.OrderService;
import com.ops.ops.utils.ExceptionCodes;
import com.ops.ops.utils.exceptions.ops.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;

    private final OfferRepository offerRepository;

    @Override
    public OrderDto create(CreateOrderRequest request) {
        String currentUsername = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        CustomerEntity customer = customerRepository
                .findByUsername(currentUsername)
                .orElseThrow(() -> new NotFoundException(
                                "Authenticated user " + currentUsername + " is not found in database",
                                ExceptionCodes.CUSTOMER_NOT_FOUND
                        )
                );

        OfferEntity offer = offerRepository
                .findByUuid(request.getOfferUuid())
                .orElseThrow(() -> new NotFoundException(
                                "Offer with uuid " + request.getOfferUuid() + " is not found in database",
                                ExceptionCodes.OFFER_NOT_FOUND
                        )
                );

        OrderEntity toBeSaved = OrderMapper.toEntity(request, customer, offer);
        toBeSaved.setCreationDate(LocalDateTime.now());

        return OrderMapper.toDto(orderRepository.save(toBeSaved));
    }

    @Override
    public OrderDto get(UUID uuid) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findByUuid(uuid);
        if (orderEntityOptional.isEmpty()) {
            throw new NotFoundException(
                    "Order with uuid " + uuid + " does not exist",
                    ExceptionCodes.ORDER_NOT_FOUND
            );
        }

        return OrderMapper.toDto(orderEntityOptional.get());
    }

    @Override
    public OrderDto update(UUID uuid, UpdateOrderRequest request) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findByUuid(uuid);
        if (orderEntityOptional.isEmpty()) {
            throw new NotFoundException(
                    "Order with uuid " + uuid + " does not exist",
                    ExceptionCodes.ORDER_NOT_FOUND
            );
        }

        OrderEntity orderEntity = orderEntityOptional.get();
        orderEntity.setStatus(request.getStatus());

        return OrderMapper.toDto(orderRepository.save(orderEntity));
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        orderRepository.deleteByUuid(uuid);
    }
}
