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
public class OfferController {

    private final OfferService offerService;

    @PostMapping(path = "/offers")
    public OfferDto createOrder(@RequestBody CreateOfferRequest request) {
        return offerService.create(request);
    }

    @GetMapping(path = "/offers")
    public OfferDto getOrder(@RequestParam("uuid") UUID uuid) {
        return offerService.get(uuid);
    }

    @PutMapping(path = "/offers")
    public OfferDto updateCustomer(@RequestParam("uuid") UUID uuid,
                                   @RequestBody UpdateOfferRequest request) {
        return offerService.update(uuid, request);
    }

    @DeleteMapping(path = "/offers")
    public void deleteCustomer(@RequestParam("uuid") UUID uuid) {
        offerService.delete(uuid);
    }
}
