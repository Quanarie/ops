package com.ops.ops.rest.controllers;

import com.ops.ops.rest.dto.offer.CreateOfferRequest;
import com.ops.ops.rest.dto.offer.UpdateOfferRequest;
import com.ops.ops.rest.dto.offer.OfferDto;
import com.ops.ops.services.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public OfferDto createOrder(@RequestBody CreateOfferRequest request) {
        return offerService.create(request);
    }

    @GetMapping
    public OfferDto getOrder(@RequestParam("uuid") UUID uuid) {
        return offerService.get(uuid);
    }

    @PutMapping
    public OfferDto updateUser(@RequestParam("uuid") UUID uuid,
                                   @RequestBody UpdateOfferRequest request) {
        return offerService.update(uuid, request);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam("uuid") UUID uuid) {
        offerService.delete(uuid);
    }
}
