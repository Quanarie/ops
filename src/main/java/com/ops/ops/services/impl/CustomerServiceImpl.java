package com.ops.ops.services.impl;

import com.ops.ops.mappers.CustomerMapper;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import com.ops.ops.rest.dto.customer.requests.CreateCustomerDto;
import com.ops.ops.rest.dto.customer.requests.UpdateCustomerDto;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto create(CreateCustomerDto request) {
        if (customerRepository.existsByNickname(request.getNickname())) {
            // exception
            return null;
        }

        CustomerEntity entity = CustomerMapper.toEntity(request);

        customerRepository.save(entity);

        return CustomerMapper.toDto(entity);
    }

    @Override
    public CustomerDto get(String nickname) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByNickname(nickname);
        if (customerEntityOptional.isEmpty()) {
            // exception
            return null;
        }

        return CustomerMapper.toDto(customerEntityOptional.get());
    }

    @Override
    public CustomerDto update(String nickname, UpdateCustomerDto request) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByNickname(nickname);

        if (customerEntityOptional.isEmpty()) {
            // exception
            return null;
        }

        CustomerEntity customerEntity = customerEntityOptional.get();
        customerEntity.setName(request.getName());
        customerEntity.setPhoneNumber(request.getPhoneNumber());
        customerEntity.setAddress(request.getAddress());

        customerRepository.save(customerEntity);

        return CustomerMapper.toDto(customerEntity);
    }

    @Override
    public void delete(String nickname) {
        customerRepository.deleteById(nickname);
    }

}