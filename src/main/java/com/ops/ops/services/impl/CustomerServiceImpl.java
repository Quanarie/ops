package com.ops.ops.services.impl;

import com.ops.ops.dto.customer.CustomerDto;
import com.ops.ops.mappers.Mapper;
import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import com.ops.ops.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Mapper<CustomerDto, CustomerEntity> customerMapper;

    @Override
    public ResponseEntity<CustomerDto> create(CustomerDto customer) {
        customerRepository.save(customerMapper.toEntity(customer));

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerDto> get(Long id) {
        Optional<CustomerEntity> optionalResult = customerRepository.findById(id);
        if (optionalResult.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CustomerDto res = customerMapper.toDto(optionalResult.get());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerDto> update(Long id, CustomerDto customer) {
        if(!customerRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        customer.setId(id);
        customerRepository.save(customerMapper.toEntity(customer));

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerDto> delete(Long id) {
        customerRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}