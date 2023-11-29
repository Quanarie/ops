package com.ops.rest.dto.user;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String name;

    private String address;

    private String username;

    private String phoneNumber;

    private UserRole role;

}
