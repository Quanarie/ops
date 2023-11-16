package com.ops.ops.services;

import com.ops.ops.rest.dto.offer.requests.CreateOfferRequest;
import com.ops.ops.rest.dto.offer.requests.UpdateOfferRequest;
import com.ops.ops.rest.dto.offer.responces.OfferDto;
import com.ops.ops.rest.dto.order.requests.CreateOrderRequest;
import com.ops.ops.rest.dto.order.requests.UpdateOrderRequest;

import java.util.UUID;

public interface OfferService {

    OfferDto create(CreateOfferRequest request);

    OfferDto get(UUID uuid);

    OfferDto update(UUID uuid, UpdateOfferRequest request);

    void delete(UUID uuid);
}
