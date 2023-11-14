package com.ops.ops;

import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.rest.dto.customer.requests.CreateCustomerRequest;
import com.ops.ops.rest.dto.customer.requests.UpdateCustomerRequest;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;

public class TestCustomers {

    public static final String testUsername = "superHero";

    public static CustomerEntity CUSTOMER_ENTITY = createCustomerEntity();

    public static CustomerDto CUSTOMER_DTO = createCustomerDto();

    public static UpdateCustomerRequest UPDATE_CUSTOMER_REQUEST = createUpdateCustomerRequest();

    public static CreateCustomerRequest CREATE_CUSTOMER_REQUEST = createCreateCustomerRequest();

    private static UpdateCustomerRequest createUpdateCustomerRequest() {
        return UpdateCustomerRequest.builder()
                .address("New York")
                .name("Peter Parker")
                .phoneNumber("1234567890")
                .build();
    }

    private static CustomerDto createCustomerDto() {
        return CustomerDto.builder()
                .address("New York")
                .username(testUsername)
                .name("Peter Parker")
                .phoneNumber("1234567890")
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
                .build();
    }

    private static CreateCustomerRequest createCreateCustomerRequest() {
        return CreateCustomerRequest.builder()
                .address("New York")
                .username(testUsername)
                .name("Peter Parker")
                .phoneNumber("1234567890")
                .password("effjehfjehfjhejffe")
                .build();
    }
}
