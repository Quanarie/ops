package com.ops.services;

import com.ops.persistence.entities.UserEntity;
import com.ops.rest.dto.user.CreateUserRequest;
import com.ops.rest.dto.user.UserDto;
import com.ops.rest.dto.user.UpdateUserRequest;

public interface UserService {

    UserDto create(CreateUserRequest request);

    UserDto get(String username);

    UserDto update(String username, UpdateUserRequest request);

    void delete(String username);

    UserEntity getByUsernameOrThrow(String username);

}
