package com.ops.ops.controllers;

import com.ops.ops.dto.customer.CustomerDto;
import com.ops.ops.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(path = "/customers")
    public void createCustomer(@RequestBody CustomerDto customer) {
        customerService.create(customer);
    }

    @GetMapping(path = "/customers")
    public CustomerDto getCustomer(@RequestParam("id") Long id) {
        return customerService.getCustomer(id);
    }
}
