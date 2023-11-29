package com.ops.rest.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CreateUserRequest {

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
    private final UserRole role;

}
