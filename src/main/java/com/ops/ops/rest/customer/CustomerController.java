package com.ops.ops.rest.customer;

import com.ops.ops.rest.dto.customer.requests.CreateCustomerDto;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.rest.dto.customer.requests.UpdateCustomerDto;
import com.ops.ops.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(path = "/customers")
    public CustomerDto createCustomer(@RequestBody CreateCustomerDto request) {
        return customerService.create(request);
    }

    @GetMapping(path = "/customers")
    public CustomerDto getCustomer(@RequestParam("username") String username) {
        return customerService.get(username);
    }

    @PutMapping(path = "/customers")
    public CustomerDto updateCustomer(@RequestParam("username") String username,
                               @RequestBody UpdateCustomerDto request) {
        return customerService.update(username, request);
    }

    @DeleteMapping(path = "/customers")
    public void deleteCustomer(@RequestParam("username") String username) {
        customerService.delete(username);
    }
}
