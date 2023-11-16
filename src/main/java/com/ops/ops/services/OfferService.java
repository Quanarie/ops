package com.ops.ops.services;

import com.ops.ops.rest.dto.offer.CreateOfferRequest;
import com.ops.ops.rest.dto.offer.UpdateOfferRequest;
import com.ops.ops.rest.dto.offer.OfferDto;

import java.util.UUID;

public interface OfferService {

    OfferDto create(CreateOfferRequest request);

    OfferDto get(UUID uuid);

    OfferDto update(UUID uuid, UpdateOfferRequest request);

    void delete(UUID uuid);
}
