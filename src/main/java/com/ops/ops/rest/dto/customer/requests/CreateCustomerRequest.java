package com.ops.ops.rest.dto.customer.requests;

import com.ops.ops.rest.dto.customer.CustomerRole;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class CreateCustomerRequest {

    private final String name;

    private final String address;

    private final String username;

    private final String password;

    private final String phoneNumber;

    private final CustomerRole role;

}
