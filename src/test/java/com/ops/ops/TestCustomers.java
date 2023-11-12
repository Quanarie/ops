package com.ops.ops;

import com.ops.ops.dto.customer.CustomerDto;
import com.ops.ops.persistence.entities.CustomerEntity;

public class TestCustomers {

    public static CustomerEntity CUSTOMER_ENTITY = createCustomerEntity();

    public static CustomerDto CUSTOMER_DTO = createCustomerDto();

    private static CustomerEntity createCustomerEntity() {
        return CustomerEntity.builder()
                .address("New York")
                .nickname("superHero")
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

}
