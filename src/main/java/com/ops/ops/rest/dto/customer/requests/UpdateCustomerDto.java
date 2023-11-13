package com.ops.ops.rest.dto.customer.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCustomerDto {

    private String name;

    private String address;

    private String phoneNumber;

}