package com.ops.rest;

import com.ops.persistence.entities.UserEntity;
import com.ops.rest.dto.user.UserRole;
import com.ops.rest.dto.user.CreateUserRequest;
import com.ops.rest.dto.user.UpdateUserRequest;
import com.ops.rest.dto.user.UserDto;

public class TestUsers {

    public static final String DEFAULT_USERNAME = "superHero";
    public static final String DEFAULT_ADDRESS = "New York";
    public static final String DEFAULT_NAME = "Peter Parker";
    public static final String DEFAULT_PHONE = "1234567890";
    public static final String DEFAULT_PASSWORD = "effjehfjehfjhejffe";
    public static final String DEFAULT_PASSWORD_HASH = "$2y$10$NV2X1k1eGHOZGbXSesc0quJkPFAXrDZ4dkA938./n5UEBUe8Vl18q";
    public static final String UPDATED_NAME = "Hulk";
    public static final String UPDATED_PHONE = "0987654321";

    public static UserDto USER_DTO = createUserDto();
    public static UserDto UPDATED_USER_DTO = createUpdatedUserDto();
    public static UserEntity USER_ENTITY = createUserEntity();
    public static CreateUserRequest CREATE_USER_REQUEST = createCreateUserRequest();
    public static UpdateUserRequest UPDATE_USER_REQUEST = createUpdateUserRequest();

    private static UserDto createUserDto() {
        return UserDto.builder()
                .address(DEFAULT_ADDRESS)
                .username(DEFAULT_USERNAME)
                .name(DEFAULT_NAME)
                .phoneNumber(DEFAULT_PHONE)
                .role(UserRole.BUYER)
                .build();
    }

    private static UserDto createUpdatedUserDto() {
        return createUserDto().toBuilder()
                .name(UPDATED_NAME)
                .phoneNumber(UPDATED_PHONE)
                .build();
    }

    private static UserEntity createUserEntity() {
        return UserEntity.builder()
                .address(DEFAULT_ADDRESS)
                .username(DEFAULT_USERNAME)
                .name(DEFAULT_NAME)
                .phoneNumber(DEFAULT_PHONE)
                .passwordHash(DEFAULT_PASSWORD_HASH)
                .role(UserRole.BUYER)
                .build();
    }

    private static CreateUserRequest createCreateUserRequest() {
        return CreateUserRequest.builder()
                .address(DEFAULT_ADDRESS)
                .username(DEFAULT_USERNAME)
                .name(DEFAULT_NAME)
                .phoneNumber(DEFAULT_PHONE)
                .password(DEFAULT_PASSWORD)
                .role(UserRole.BUYER)
                .build();
    }

    private static UpdateUserRequest createUpdateUserRequest() {
        return UpdateUserRequest.builder()
                .address(DEFAULT_ADDRESS)
                .name(UPDATED_NAME)
                .phoneNumber(UPDATED_PHONE)
                .build();
    }
}

