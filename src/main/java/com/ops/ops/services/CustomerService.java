package com.ops.ops.services;

import com.ops.ops.rest.dto.customer.requests.CreateCustomerDto;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.rest.dto.customer.requests.UpdateCustomerDto;

public interface CustomerService {

    CustomerDto create(CreateCustomerDto request);

    CustomerDto get(String username);

    CustomerDto update(String username, UpdateCustomerDto request);

    void delete(String username);

}
