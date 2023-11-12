package com.ops.ops.rest.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCustomerRequest {

    private String name;

    private String phoneNumber;

    private String address;

}
