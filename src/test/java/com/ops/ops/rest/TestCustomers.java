package com.ops.ops.rest;

import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.rest.dto.customer.CustomerRole;
import com.ops.ops.rest.dto.customer.requests.CreateCustomerRequest;
import com.ops.ops.rest.dto.customer.requests.UpdateCustomerRequest;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;

public class TestCustomers {

    public static final String DEFAULT_USERNAME = "superHero";
    public static final String DEFAULT_ADDRESS = "New York";
    public static final String DEFAULT_NAME = "Peter Parker";
    public static final String DEFAULT_PHONE = "1234567890";
    public static final String DEFAULT_PASSWORD = "effjehfjehfjhejffe";
    public static final String DEFAULT_PASSWORD_HASH = "$2y$10$NV2X1k1eGHOZGbXSesc0quJkPFAXrDZ4dkA938./n5UEBUe8Vl18q";
    public static final String UPDATED_NAME = "Hulk";
    public static final String UPDATED_PHONE = "0987654321";

    public static CustomerDto CUSTOMER_DTO = createCustomerDto();
    public static CustomerDto UPDATED_CUSTOMER_DTO = createUpdatedCustomerDto();
    public static CustomerEntity CUSTOMER_ENTITY = createCustomerEntity();
    public static CreateCustomerRequest CREATE_CUSTOMER_REQUEST = createCreateCustomerRequest();
    public static UpdateCustomerRequest UPDATE_CUSTOMER_REQUEST = createUpdateCustomerRequest();

    private static CustomerDto createCustomerDto() {
        return CustomerDto.builder()
                .address(DEFAULT_ADDRESS)
                .username(DEFAULT_USERNAME)
                .name(DEFAULT_NAME)
                .phoneNumber(DEFAULT_PHONE)
                .role(CustomerRole.BUYER)
                .build();
    }

    private static CustomerDto createUpdatedCustomerDto() {
        return createCustomerDto().toBuilder()
                .name(UPDATED_NAME)
                .phoneNumber(UPDATED_PHONE)
                .build();
    }

    private static CustomerEntity createCustomerEntity() {
        return CustomerEntity.builder()
                .address(DEFAULT_ADDRESS)
                .username(DEFAULT_USERNAME)
                .name(DEFAULT_NAME)
                .phoneNumber(DEFAULT_PHONE)
                .passwordHash(DEFAULT_PASSWORD_HASH)
                .role(CustomerRole.BUYER)
                .build();
    }

    private static CreateCustomerRequest createCreateCustomerRequest() {
        return CreateCustomerRequest.builder()
                .address(DEFAULT_ADDRESS)
                .username(DEFAULT_USERNAME)
                .name(DEFAULT_NAME)
                .phoneNumber(DEFAULT_PHONE)
                .password(DEFAULT_PASSWORD)
                .role(CustomerRole.BUYER)
                .build();
    }

    private static UpdateCustomerRequest createUpdateCustomerRequest() {
        return UpdateCustomerRequest.builder()
                .address(DEFAULT_ADDRESS)
                .name(UPDATED_NAME)
                .phoneNumber(UPDATED_PHONE)
                .build();
    }
}

