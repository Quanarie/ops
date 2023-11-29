package com.ops.rest.controllers;

import com.ops.rest.dto.user.CreateUserRequest;
import com.ops.rest.dto.user.UserDto;
import com.ops.rest.dto.user.UpdateUserRequest;
import com.ops.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.create(request);
    }

    @GetMapping
    @PreAuthorize("#username == authentication.principal.username")
    public UserDto getUser(@RequestParam("username") @NotBlank String username) {
        return userService.get(username);
    }

    @PutMapping
    @PreAuthorize("#username == authentication.principal.username")
    public UserDto updateUser(@RequestParam("username") @NotBlank String username,
                              @RequestBody UpdateUserRequest request) {
        return userService.update(username, request);
    }

    @DeleteMapping
    @PreAuthorize("#username == authentication.principal.username")
    public void deleteUser(@RequestParam("username") @NotBlank String username) {
        userService.delete(username);
    }
}
