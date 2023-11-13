package com.ops.ops.rest.dto.customer.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCustomerDto {

    private String name;

    private String address;

    private String username;

    private String phoneNumber;
}
