package com.ops.ops.mappers;

import com.ops.ops.rest.dto.customer.requests.CreateCustomerDto;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.persistence.entities.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper  {

    public static CustomerEntity toEntity(CustomerDto customerDto) {
        return CustomerEntity.builder()
                .address(customerDto.getAddress())
                .username(customerDto.getUsername())
                .name(customerDto.getName())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
    }

    public static CustomerDto toDto(CustomerEntity customerEntity) {
        return CustomerDto.builder()
                .address(customerEntity.getAddress())
                .username(customerEntity.getUsername())
                .name(customerEntity.getName())
                .phoneNumber(customerEntity.getPhoneNumber())
                .build();
    }

    public static CustomerEntity toEntity(CreateCustomerDto request) {
        return CustomerEntity.builder()
                .address(request.getAddress())
                .username(request.getUsername())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

}