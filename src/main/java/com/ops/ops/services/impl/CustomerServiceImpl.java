package com.ops.ops.services.impl;

import com.ops.ops.mappers.CustomerMapper;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import com.ops.ops.rest.dto.customer.requests.CreateCustomerRequest;
import com.ops.ops.rest.dto.customer.requests.UpdateCustomerRequest;
import com.ops.ops.rest.dto.customer.responces.CustomerDto;
import com.ops.ops.services.CustomerService;
import com.ops.ops.exceptions.ExceptionCodes;
import com.ops.ops.exceptions.ConflictException;
import com.ops.ops.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public CustomerDto create(CreateCustomerRequest request) {
        if (customerRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ConflictException(
                    "Username " + request.getUsername() + " is already taken",
                    ExceptionCodes.CUSTOMER_ALREADY_EXISTS
            );
        }

        String encodedPassword = encoder.encode(request.getPassword());
        CustomerEntity entity = customerRepository.save(CustomerMapper.toEntity(request, encodedPassword));

        return CustomerMapper.toDto(entity);
    }

    @Override
    @PreAuthorize("#username == authentication.principal.username")
    public CustomerDto get(String username) {
        CustomerEntity entity = customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                                "Profile with username " + username + " does not exist",
                                ExceptionCodes.CUSTOMER_NOT_FOUND
                        )
                );

        return CustomerMapper.toDto(entity);
    }

    @Override
    @PreAuthorize("#username == authentication.principal.username")
    public CustomerDto update(String username, UpdateCustomerRequest request) {
        CustomerEntity entity = customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                                "Profile with username " + username + " does not exist",
                                ExceptionCodes.CUSTOMER_NOT_FOUND
                        )
                );

        if (null != request.getName())
            entity.setName(request.getName());

        if (null != request.getPhoneNumber())
            entity.setPhoneNumber(request.getPhoneNumber());

        if (null != request.getAddress())
            entity.setAddress(request.getAddress());

        return CustomerMapper.toDto(customerRepository.save(entity));
    }

    @Override
    @Transactional
    @PreAuthorize("#username == authentication.principal.username")
    public void delete(String username) {
        customerRepository.deleteByUsername(username);
    }
}