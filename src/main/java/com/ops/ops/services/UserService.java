package com.ops.ops.services;

import com.ops.ops.rest.dto.user.CreateUserRequest;
import com.ops.ops.rest.dto.user.UserDto;
import com.ops.ops.rest.dto.user.UpdateUserRequest;

public interface UserService {

    UserDto create(CreateUserRequest request);

    UserDto get(String username);

    UserDto update(String username, UpdateUserRequest request);

    void delete(String username);

}
