package com.ops.ops.services.impl;

import com.ops.ops.dto.customer.CustomerDto;
import com.ops.ops.dto.customer.UpdateCustomerRequest;
import com.ops.ops.mappers.CustomerMapper;
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

    @Override
    public ResponseEntity<CustomerDto> create(CustomerDto customer) {
        if(customerRepository.existsByNickname(customer.getNickname())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        customerRepository.save(CustomerMapper.dtoToEntity(customer));

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerDto> get(String nickname) {
        Optional<CustomerEntity> optionalResult = customerRepository.findByNickname(nickname);
        if (optionalResult.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CustomerDto res = CustomerMapper.entityToDto(optionalResult.get());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerDto> update(String nickname, UpdateCustomerRequest request) {
        if(!customerRepository.existsByNickname(nickname)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CustomerEntity customerEntity = CustomerMapper.updateRequestToEntity(request);
        customerEntity.setNickname(nickname);

        customerRepository.save(customerEntity);

        return new ResponseEntity<>(CustomerMapper.entityToDto(customerEntity), HttpStatus.OK);
        
    }

    @Override
    public ResponseEntity<CustomerDto> delete(String nickname) {
        customerRepository.deleteById(nickname);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}