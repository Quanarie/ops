package com.ops.ops.mappers.impl;

import com.ops.ops.dto.customer.CustomerDto;
import com.ops.ops.mappers.Mapper;
import com.ops.ops.persistence.entities.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapperImpl implements Mapper<CustomerDto, CustomerEntity> {

    @Override
    public CustomerEntity toEntity(CustomerDto customerDto) {
        return CustomerEntity.builder()
                .address(customerDto.getAddress())
                .id(customerDto.getId())
                .name(customerDto.getName())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
    }

    @Override
    public CustomerDto toDto(CustomerEntity customerEntity) {
        return CustomerDto.builder()
                .address(customerEntity.getAddress())
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .phoneNumber(customerEntity.getPhoneNumber())
                .build();
    }
}
