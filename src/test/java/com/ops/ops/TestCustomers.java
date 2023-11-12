package com.ops.ops;

import com.ops.ops.dto.customer.CustomerDto;
import com.ops.ops.persistence.entities.CustomerEntity;

public class TestCustomers {

    public static CustomerEntity CUSTOMER_ENTITY = createCustomerEntity();

    private static CustomerEntity createCustomerEntity() {
        return CustomerEntity.builder()
                .address("New York")
                .nickname("superHero")
                .name("Peter Parker")
                .phoneNumber("1234567890")
                .build();
    }

}
