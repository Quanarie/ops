package com.ops.ops.dto.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCustomerRequest {

    private String name;

    private String address;

    private String phoneNumber;

}
