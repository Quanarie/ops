package com.ops.ops.controllers;

import com.ops.ops.dto.customer.CustomerDto;
import com.ops.ops.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(path = "/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customer) {
        return customerService.create(customer);
    }

    @GetMapping(path = "/customers")
    public ResponseEntity<CustomerDto> getCustomer(@RequestParam("id") Long id) {
        return customerService.get(id);
    }

    @PutMapping(path = "/customers")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestParam("id") Long id,
                                      @RequestBody CustomerDto customer) {
        return customerService.update(id, customer);
    }

    @DeleteMapping(path = "/customers")
    public ResponseEntity deleteCustomer(@RequestParam("id") Long id) {
        return customerService.delete(id);
    }
}
