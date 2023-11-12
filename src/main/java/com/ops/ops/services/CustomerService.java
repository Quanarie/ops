package com.ops.ops.services;

import com.ops.ops.dto.customer.CustomerDto;
import com.ops.ops.mappers.Mapper;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final Mapper<CustomerDto, CustomerEntity> customerMapper;

    public void create(CustomerDto customer) {
        customerRepository.save(customerMapper.toEntity(customer));
    }

    public CustomerDto getCustomer(Long id) {
        Optional<CustomerEntity> byId = customerRepository.findById(id);
        return customerMapper.toDto(byId.orElse(null));
    }
}
