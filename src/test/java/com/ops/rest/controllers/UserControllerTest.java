package com.ops.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.rest.TestUsers;
import com.ops.persistence.entities.UserEntity;
import com.ops.persistence.repositories.UserRepository;
import com.ops.rest.dto.user.CreateUserRequest;
import com.ops.rest.dto.user.UpdateUserRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    void shouldCreateUser() throws Exception {
        CreateUserRequest request = TestUsers.CREATE_USER_REQUEST;

        String response = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(response, objectMapper.writeValueAsString(TestUsers.USER_DTO));
    }

    @Test
    void shouldNotCreateUsersWithSameUsername() throws Exception {
        userRepository.save(TestUsers.USER_ENTITY);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestUsers.CREATE_USER_REQUEST)))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(value = TestUsers.DEFAULT_USERNAME)
    void shouldGetUser() throws Exception {
        userRepository.save(TestUsers.USER_ENTITY);

        String response = mockMvc.perform(get("/users")
                        .param("username", TestUsers.USER_ENTITY.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(response, objectMapper.writeValueAsString(TestUsers.USER_DTO));
    }

    @Test
    @WithMockUser(value = TestUsers.DEFAULT_USERNAME)
    void shouldNotGetIfNotInDatabase() throws Exception {
        mockMvc.perform(get("/users")
                        .param("username", TestUsers.USER_ENTITY.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = TestUsers.DEFAULT_USERNAME)
    void shouldUpdateUser() throws Exception {
        userRepository.save(TestUsers.USER_ENTITY);

        UpdateUserRequest request = TestUsers.UPDATE_USER_REQUEST;

        String response = mockMvc.perform(put("/users")
                        .param("username", TestUsers.USER_ENTITY.getUsername())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(response, objectMapper.writeValueAsString(TestUsers.UPDATED_USER_DTO));
    }

    @Test
    @WithMockUser(value = TestUsers.DEFAULT_USERNAME)
    void shouldDeleteUser() throws Exception {
        UserEntity saved = userRepository.save(TestUsers.USER_ENTITY);

        mockMvc.perform(delete("/users")
                        .param("username", TestUsers.USER_ENTITY.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertFalse(userRepository.existsById(saved.getId()));
    }
}
