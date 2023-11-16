package com.ops.ops.rest.dto.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UpdateCustomerRequest {

    @NotBlank
    private final String name;

    @NotBlank
    private final String address;

    @NotBlank
    private final String phoneNumber;

}
