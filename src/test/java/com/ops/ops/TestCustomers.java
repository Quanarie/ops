package com.ops.ops;

import com.ops.ops.rest.dto.customer.requests.UpdateCustomerDto;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.persistence.entities.CustomerEntity;

public class TestCustomers {

    public static CustomerEntity CUSTOMER_ENTITY = createCustomerEntity();
    public static CustomerDto CUSTOMER_DTO = createCustomerDto();

    public static UpdateCustomerDto UPDATE_CUSTOMER_DTO = createUpdateCustomerDto();

    private static UpdateCustomerDto createUpdateCustomerDto() {
        return UpdateCustomerDto.builder()
                .address("New York")
                .name("Peter Parker")
                .phoneNumber("1234567890")
                .build();
    }

    private static CustomerDto createCustomerDto() {
        return CustomerDto.builder()
                .address("New York")
                .nickname("superHero")
                .name("Peter Parker")
                .phoneNumber("1234567890")
                .build();
    }

    private static CustomerEntity createCustomerEntity() {
        return CustomerEntity.builder()
                .id(1L)
                .address("New York")
                .nickname("superHero")
                .name("Peter Parker")
                .phoneNumber("1234567890")
                .build();
    }

}
