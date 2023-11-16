package com.ops.ops.services.impl;

import com.ops.ops.mappers.OfferMapper;
import com.ops.ops.persistence.entities.OfferEntity;
import com.ops.ops.persistence.repositories.OfferRepository;
import com.ops.ops.rest.dto.offer.requests.CreateOfferRequest;
import com.ops.ops.rest.dto.offer.requests.UpdateOfferRequest;
import com.ops.ops.rest.dto.offer.responces.OfferDto;
import com.ops.ops.services.OfferService;
import com.ops.ops.utils.ExceptionCodes;
import com.ops.ops.utils.exceptions.ops.exceptions.NotFoundException;
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
        entity.setTitle(request.getTitle());
        entity.setPrice(request.getPrice());

        offerRepository.save(entity);

        return OfferMapper.toDto(entity);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public void delete(UUID uuid) {
        offerRepository.deleteByUuid(uuid);
    }
}
