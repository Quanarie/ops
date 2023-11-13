package com.ops.ops.services;

import com.ops.ops.rest.dto.customer.requests.CreateCustomerDto;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.rest.dto.customer.requests.UpdateCustomerDto;

public interface CustomerService {

    CustomerDto create(CreateCustomerDto request);

    CustomerDto get(String nickname);

    CustomerDto update(String nickname, UpdateCustomerDto request);

    void delete(String nickname);

}
