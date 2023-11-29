package com.ops.services.impl;

import com.ops.mappers.UserMapper;
import com.ops.persistence.entities.UserEntity;
import com.ops.persistence.repositories.UserRepository;
import com.ops.rest.dto.user.CreateUserRequest;
import com.ops.rest.dto.user.UpdateUserRequest;
import com.ops.rest.dto.user.UserDto;
import com.ops.services.UserService;
import com.ops.exceptions.ExceptionCodes;
import com.ops.exceptions.ConflictException;
import com.ops.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDto create(CreateUserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ConflictException(
                    "Username " + request.getUsername() + " is already taken",
                    ExceptionCodes.USER_ALREADY_EXISTS
            );
        }

        String encodedPassword = encoder.encode(request.getPassword());
        UserEntity entity = userRepository.save(UserMapper.toEntity(request, encodedPassword));

        return UserMapper.toDto(entity);
    }

    @Override
    public UserDto get(String username) {
        UserEntity entity = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                                "Profile with username " + username + " does not exist",
                                ExceptionCodes.USER_NOT_FOUND
                        )
                );

        return UserMapper.toDto(entity);
    }

    @Override
    public UserDto update(String username, UpdateUserRequest request) {
        UserEntity entity = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                                "Profile with username " + username + " does not exist",
                                ExceptionCodes.USER_NOT_FOUND
                        )
                );

        if (null != request.getName())
            entity.setName(request.getName());

        if (null != request.getPhoneNumber())
            entity.setPhoneNumber(request.getPhoneNumber());

        if (null != request.getAddress())
            entity.setAddress(request.getAddress());

        return UserMapper.toDto(userRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }
}