package com.ops.ops.services;

import com.ops.ops.rest.dto.customer.requests.CreateCustomerRequest;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.rest.dto.customer.requests.UpdateCustomerRequest;

public interface CustomerService {

    CustomerDto create(CreateCustomerRequest request);

    CustomerDto get(String username);

    CustomerDto update(String username, UpdateCustomerRequest request);

    void delete(String username);

}
