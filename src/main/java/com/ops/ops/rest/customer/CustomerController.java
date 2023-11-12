package com.ops.ops.rest.customer;

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
    public ResponseEntity<CustomerDto> getCustomer(@RequestParam("nickname") String nickname) {
        return customerService.get(nickname);
    }

    @PutMapping(path = "/customers")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestParam("nickname") String nickname,
                                                      @RequestBody CustomerDto customer) {
        return customerService.update(nickname, customer);
    }

    @DeleteMapping(path = "/customers")
    public ResponseEntity deleteCustomer(@RequestParam("nickname") String nickname) {
        return customerService.delete(nickname);
    }
}
