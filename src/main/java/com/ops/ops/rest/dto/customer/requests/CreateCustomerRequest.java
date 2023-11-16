package com.ops.ops.rest.dto.customer.requests;

import com.ops.ops.rest.dto.customer.CustomerRole;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class CreateCustomerRequest {

    private String name;

    private String address;

    private String username;

    private String password;

    private String phoneNumber;

    private CustomerRole role;

}
