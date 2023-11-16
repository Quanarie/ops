package com.ops.ops;

import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.rest.dto.customer.CustomerRole;
import com.ops.ops.rest.dto.customer.requests.CreateCustomerRequest;
import com.ops.ops.rest.dto.customer.requests.UpdateCustomerRequest;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;

public class TestCustomers {

    public static final String testUsername = "superHero";

    public static CustomerEntity CUSTOMER_ENTITY = createCustomerEntity();

    public static CustomerDto CUSTOMER_DTO = createCustomerDto();

    public static CustomerDto UPDATED_CUSTOMER_DTO = createUpdatedCustomerDto();

    public static CreateCustomerRequest CREATE_CUSTOMER_REQUEST = createCreateCustomerRequest();

    public static UpdateCustomerRequest UPDATE_CUSTOMER_REQUEST = createUpdateCustomerRequest();

    private static CreateCustomerRequest createCreateCustomerRequest() {
        return CreateCustomerRequest.builder()
                .address("New York")
                .username(testUsername)
                .name("Peter Parker")
                .phoneNumber("1234567890")
                .password("effjehfjehfjhejffe")
                .role(CustomerRole.BUYER)
                .build();
    }

    private static UpdateCustomerRequest createUpdateCustomerRequest() {
        return UpdateCustomerRequest.builder()
                .address("New York")
                .name("Hulk")
                .phoneNumber("0987654321")
                .build();
    }

    private static CustomerDto createUpdatedCustomerDto() {
        return CustomerDto.builder()
                .address("New York")
                .username(testUsername)
                .name("Hulk")
                .phoneNumber("0987654321")
                .role(CustomerRole.BUYER)
                .build();
    }

    private static CustomerDto createCustomerDto() {
        return CustomerDto.builder()
                .address("New York")
                .username(testUsername)
                .name("Peter Parker")
                .phoneNumber("1234567890")
                .role(CustomerRole.BUYER)
                .build();
    }

    private static CustomerEntity createCustomerEntity() {
        return CustomerEntity.builder()
                .id(1L)
                .address("New York")
                .username(testUsername)
                .name("Peter Parker")
                .phoneNumber("1234567890")
                .passwordHash("$2y$10$NV2X1k1eGHOZGbXSesc0quJkPFAXrDZ4dkA938./n5UEBUe8Vl18q")
                .role(CustomerRole.BUYER)
                .build();
    }
}

