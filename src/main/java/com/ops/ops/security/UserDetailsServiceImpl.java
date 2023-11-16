package com.ops.ops.security;

import com.ops.ops.persistence.entities.CustomerEntity;
import com.ops.ops.persistence.repositories.CustomerRepository;
import com.ops.ops.utils.ExceptionCodes;
import com.ops.ops.utils.exceptions.ops.exceptions.NotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        CustomerEntity customerEntity = customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                                "Profile with username " + username + " does not exist",
                                ExceptionCodes.CUSTOMER_NOT_FOUND
                        )
                );

        return new User(
                customerEntity.getUsername(),
                customerEntity.getPasswordHash(),
                Collections.singletonList(
                        new SimpleGrantedAuthority(
                                "ROLE_" + customerEntity.getRole().toString()
                        )
                )
        );
    }
}
