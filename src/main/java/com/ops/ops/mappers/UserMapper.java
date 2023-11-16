package com.ops.ops.mappers;

import com.ops.ops.persistence.entities.UserEntity;
import com.ops.ops.rest.dto.user.CreateUserRequest;
import com.ops.ops.rest.dto.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper  {

    public static UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .address(userEntity.getAddress())
                .username(userEntity.getUsername())
                .name(userEntity.getName())
                .phoneNumber(userEntity.getPhoneNumber())
                .role(userEntity.getRole())
                .build();
    }

    public static UserEntity toEntity(CreateUserRequest request, String passwordHash) {
        return UserEntity.builder()
                .address(request.getAddress())
                .username(request.getUsername())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .passwordHash(passwordHash)
                .role(request.getRole())
                .build();
    }
}