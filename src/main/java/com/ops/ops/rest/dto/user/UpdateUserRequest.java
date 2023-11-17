package com.ops.ops.rest.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UpdateUserRequest {

    private final String name;

    private final String address;

    private final String phoneNumber;

}
