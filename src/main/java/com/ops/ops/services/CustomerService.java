package com.ops.ops.services;

import com.ops.ops.dto.customer.CustomerDto;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    ResponseEntity<CustomerDto> create(CustomerDto customer);

    ResponseEntity<CustomerDto> get(String nickname);

    ResponseEntity<CustomerDto> update(String nickname, CustomerDto customer);

    ResponseEntity<CustomerDto> delete(String nickname);

}
