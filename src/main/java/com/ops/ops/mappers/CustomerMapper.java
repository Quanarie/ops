package com.ops.ops.mappers;

import com.ops.ops.dto.customer.CustomerDto;
import com.ops.ops.dto.customer.UpdateCustomerRequest;
import com.ops.ops.persistence.entities.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper  {

    public static CustomerEntity dtoToEntity(CustomerDto customerDto) {
        return CustomerEntity.builder()
                .address(customerDto.getAddress())
                .nickname(customerDto.getNickname())
                .name(customerDto.getName())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
    }

    public static CustomerDto entityToDto(CustomerEntity customerEntity) {
        return CustomerDto.builder()
                .address(customerEntity.getAddress())
                .nickname(customerEntity.getNickname())
                .name(customerEntity.getName())
                .phoneNumber(customerEntity.getPhoneNumber())
                .build();
    }

    public static CustomerEntity updateRequestToEntity(UpdateCustomerRequest request) {
        return CustomerEntity.builder()
                .address(request.getAddress())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }
}