package com.ops.ops.services.impl;

import com.ops.ops.mappers.CustomerMapper;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import com.ops.ops.rest.dto.customer.requests.CreateCustomerDto;
import com.ops.ops.rest.dto.customer.requests.UpdateCustomerDto;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.services.CustomerService;
import com.ops.ops.utils.ExceptionCodes;
import com.ops.ops.utils.exceptions.CustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto create(CreateCustomerDto request) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByUsername(request.getUsername());
        if (customerEntityOptional.isPresent()) {
            throw new CustomerException(
                    "Username " + request.getUsername() + " is already taken",
                    ExceptionCodes.CUSTOMER_ALREADY_EXISTS,
                    HttpStatus.CONFLICT
            );
        }

        CustomerEntity customerEntity = CustomerMapper.toEntity(request);

        customerRepository.save(customerEntity);

        return CustomerMapper.toDto(customerEntity);
    }

    @Override
    public CustomerDto get(String username) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByUsername(username);
        if (customerEntityOptional.isEmpty()) {
            throw new CustomerException(
                    "Profile with username " + username + " does not exist",
                    ExceptionCodes.CUSTOMER_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }

        return CustomerMapper.toDto(customerEntityOptional.get());
    }

    @Override
    public CustomerDto update(String username, UpdateCustomerDto request) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByUsername(username);
        if (customerEntityOptional.isEmpty()) {
            throw new CustomerException(
                    "Profile with username " + username + " does not exist",
                    ExceptionCodes.CUSTOMER_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }

        CustomerEntity customerEntity = customerEntityOptional.get();
        customerEntity.setName(request.getName());
        customerEntity.setPhoneNumber(request.getPhoneNumber());
        customerEntity.setAddress(request.getAddress());

        customerRepository.save(customerEntity);

        return CustomerMapper.toDto(customerEntity);
    }

    @Override
    public void delete(String username) {
        customerRepository.deleteByUsername(username);
    }
}