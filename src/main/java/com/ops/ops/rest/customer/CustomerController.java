package com.ops.ops.rest.customer;

import com.ops.ops.dto.customer.CustomerDto;
import com.ops.ops.dto.customer.UpdateCustomerRequest;
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
    public ResponseEntity<CustomerDto> getCustomer(@RequestParam("nickname") String nickname) {
        return customerService.get(nickname);
    }

    // Without nickname change
    @PutMapping(path = "/customers")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestParam("nickname") String nickname,
                                                      @RequestBody UpdateCustomerRequest request) {
        return customerService.update(nickname, request);
    }

    @DeleteMapping(path = "/customers")
    public ResponseEntity deleteCustomer(@RequestParam("nickname") String nickname) {
        return customerService.delete(nickname);
    }
}
