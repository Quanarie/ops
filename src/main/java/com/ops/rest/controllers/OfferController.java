package com.ops.rest.controllers;

import com.ops.rest.dto.offer.CreateOfferRequest;
import com.ops.rest.dto.offer.OfferDto;
import com.ops.rest.dto.offer.UpdateOfferRequest;
import com.ops.services.OfferService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public OfferDto createOrder(@Valid @RequestBody CreateOfferRequest request) {
        return offerService.create(request);
    }

    @GetMapping
    public OfferDto getOrder(@RequestParam("uuid") @NotNull UUID uuid) {
        return offerService.get(uuid);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public OfferDto updateUser(@RequestParam("uuid") @NotNull UUID uuid,
                               @RequestBody UpdateOfferRequest request) {
        return offerService.update(uuid, request);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public void deleteUser(@RequestParam("uuid") @NotNull UUID uuid) {
        offerService.delete(uuid);
    }
}
