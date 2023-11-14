package com.ops.ops.mappers;

import com.ops.ops.rest.dto.customer.requests.CreateCustomerRequest;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.persistence.entities.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper  {

    public static CustomerDto toDto(CustomerEntity customerEntity) {
        return CustomerDto.builder()
                .address(customerEntity.getAddress())
                .username(customerEntity.getUsername())
                .name(customerEntity.getName())
                .phoneNumber(customerEntity.getPhoneNumber())
                .role(customerEntity.getRole())
                .build();
    }

    public static CustomerEntity toEntity(CreateCustomerRequest request) {
        return CustomerEntity.builder()
                .address(request.getAddress())
                .username(request.getUsername())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .passwordHash((new BCryptPasswordEncoder()).encode(request.getPassword())) // ASK
                .role(request.getRole())
                .build();
    }
}