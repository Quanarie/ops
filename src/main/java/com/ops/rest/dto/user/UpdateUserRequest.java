package com.ops.rest.dto.user;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UpdateUserRequest {

    private final String name;

    private final String address;

    private final String phoneNumber;

}
