package com.ops.ops.rest.dto.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CreateCustomerRequest {

    @NotBlank
    private final String name;

    @NotBlank
    private final String address;

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

    @NotBlank
    private final String phoneNumber;

    @NotNull
    private final CustomerRole role;

}
