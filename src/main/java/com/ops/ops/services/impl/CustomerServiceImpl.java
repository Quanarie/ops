package com.ops.ops.services.impl;

import com.ops.ops.mappers.CustomerMapper;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import com.ops.ops.rest.dto.customer.requests.CreateCustomerRequest;
import com.ops.ops.rest.dto.customer.requests.UpdateCustomerRequest;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.services.CustomerService;
import com.ops.ops.utils.ExceptionCodes;
import com.ops.ops.utils.exceptions.ops.exceptions.ConflictException;
import com.ops.ops.utils.exceptions.ops.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto create(CreateCustomerRequest request) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByUsername(request.getUsername());
        if (customerEntityOptional.isPresent()) {
            throw new ConflictException(
                    "Username " + request.getUsername() + " is already taken",
                    ExceptionCodes.CUSTOMER_ALREADY_EXISTS
            );
        }

        customerRepository.save(CustomerMapper.toEntity(request));

        return CustomerMapper.toDto(customerRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException(
                                "Problem with creation of user " + request.getUsername(),
                                ExceptionCodes.CUSTOMER_NOT_FOUND
                        )
                )
        );
    }

    @Override
    @PreAuthorize("#username == authentication.principal.username")
    public CustomerDto get(String username) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByUsername(username);
        if (customerEntityOptional.isEmpty()) {
            throw new NotFoundException(
                    "Profile with username " + username + " does not exist",
                    ExceptionCodes.CUSTOMER_NOT_FOUND
            );
        }

        return CustomerMapper.toDto(customerEntityOptional.get());
    }

    @Override
    @PreAuthorize("#username == authentication.principal.username")
    public CustomerDto update(String username, UpdateCustomerRequest request) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByUsername(username);
        if (customerEntityOptional.isEmpty()) {
            throw new NotFoundException(
                    "Profile with username " + username + " does not exist",
                    ExceptionCodes.CUSTOMER_NOT_FOUND
            );
        }

        CustomerEntity customerEntity = customerEntityOptional.get();
        customerEntity.setName(request.getName());
        customerEntity.setPhoneNumber(request.getPhoneNumber());
        customerEntity.setAddress(request.getAddress());

        customerRepository.save(customerEntity);

        return CustomerMapper.toDto(customerRepository.findByUsername(customerEntity.getUsername()) // ASK
                .orElseThrow(() -> new NotFoundException(
                                "Problem with getting user " + customerEntity.getUsername(),
                                ExceptionCodes.CUSTOMER_NOT_FOUND
                        )
                )
        );
    }

    @Override
    @Transactional
    @PreAuthorize("#username == authentication.principal.username")
    public void delete(String username) {
        customerRepository.deleteByUsername(username);
    }
}