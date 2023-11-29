package com.ops.services.impl;

import com.ops.mappers.OfferMapper;
import com.ops.persistence.entities.OfferEntity;
import com.ops.persistence.repositories.OfferRepository;
import com.ops.rest.dto.offer.CreateOfferRequest;
import com.ops.rest.dto.offer.UpdateOfferRequest;
import com.ops.rest.dto.offer.OfferDto;
import com.ops.services.OfferService;
import com.ops.exceptions.ExceptionCodes;
import com.ops.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public OfferDto create(CreateOfferRequest request) {
        OfferEntity entity = offerRepository.save(OfferMapper.toEntity(request));

        return OfferMapper.toDto(entity);
    }

    @Override
    public OfferDto get(UUID uuid) {
        OfferEntity entity = offerRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(
                                "Offer with uuid " + uuid + " not found",
                                ExceptionCodes.OFFER_NOT_FOUND
                        )
                );

        return OfferMapper.toDto(entity);
    }

    @Override
    public OfferDto update(UUID uuid, UpdateOfferRequest request) {
        OfferEntity entity = offerRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(
                                "Offer with uuid " + uuid + " not found",
                                ExceptionCodes.OFFER_NOT_FOUND
                        )
                );

        if (null != request.getTitle())
            entity.setTitle(request.getTitle());
        if (null != request.getPrice())
            entity.setPrice(request.getPrice());

        return OfferMapper.toDto(offerRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        offerRepository.deleteByUuid(uuid);
    }

    @Override
    public OfferEntity getByUuidOrThrow(UUID uuid) {
        return offerRepository
                .findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(
                                "Offer with uuid " + uuid + " not found",
                                ExceptionCodes.OFFER_NOT_FOUND
                        )
                );
    }
}
