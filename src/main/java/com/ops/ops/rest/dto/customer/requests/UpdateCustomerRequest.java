package com.ops.ops.rest.dto.customer.requests;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class UpdateCustomerRequest {    // TODO ASK нулі

    private String name;

    private String address;

    private String phoneNumber;

}
