package com.ops.ops.rest.dto.customer.requests;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class UpdateCustomerRequest {

    private final String name;

    private final String address;

    private final String phoneNumber;

}
