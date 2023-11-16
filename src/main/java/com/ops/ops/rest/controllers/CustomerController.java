package com.ops.ops.rest.controllers;

import com.ops.ops.rest.dto.customer.CreateCustomerRequest;
import com.ops.ops.rest.dto.customer.CustomerDto;
import com.ops.ops.rest.dto.customer.UpdateCustomerRequest;
import com.ops.ops.services.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerDto createCustomer(@Valid @RequestBody CreateCustomerRequest request) {  // ASK random http answer
        return customerService.create(request);
    }

    @GetMapping
    public CustomerDto getCustomer(@RequestParam("username") @NotBlank String username) {   // ASK why 500 not 400
        return customerService.get(username);
    }

    @PutMapping
    public CustomerDto updateCustomer(@RequestParam("username") @NotBlank String username,
                                      @Valid @RequestBody UpdateCustomerRequest request) {
        return customerService.update(username, request);
    }

    @DeleteMapping
    public void deleteCustomer(@RequestParam("username") @NotBlank String username) {
        customerService.delete(username);
    }
}
