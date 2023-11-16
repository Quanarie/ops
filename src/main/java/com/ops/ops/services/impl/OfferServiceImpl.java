package com.ops.ops.services.impl;

import com.ops.ops.mappers.OfferMapper;
import com.ops.ops.persistence.entities.OfferEntity;
import com.ops.ops.persistence.repositories.OfferRepository;
import com.ops.ops.rest.dto.offer.CreateOfferRequest;
import com.ops.ops.rest.dto.offer.UpdateOfferRequest;
import com.ops.ops.rest.dto.offer.OfferDto;
import com.ops.ops.services.OfferService;
import com.ops.ops.exceptions.ExceptionCodes;
import com.ops.ops.exceptions.NotFoundException;
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
    @PreAuthorize("hasRole('ROLE_SELLER')")
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
    @PreAuthorize("hasRole('ROLE_SELLER')")
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
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public void delete(UUID uuid) {
        offerRepository.deleteByUuid(uuid);
    }
}
