package com.ops.ops.services;

import com.ops.ops.dto.customer.CustomerDto;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    ResponseEntity<CustomerDto> create(CustomerDto customer);

    ResponseEntity<CustomerDto> get(Long id);

    ResponseEntity<CustomerDto> update(Long id, CustomerDto customer);

    ResponseEntity<CustomerDto> delete(Long id);

}
