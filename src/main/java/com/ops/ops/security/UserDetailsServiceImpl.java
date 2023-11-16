package com.ops.ops.security;

import com.ops.ops.persistence.entities.UserEntity;
import com.ops.ops.persistence.repositories.UserRepository;
import com.ops.ops.exceptions.ExceptionCodes;
import com.ops.ops.exceptions.NotFoundException;
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

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                                "Profile with username " + username + " does not exist",
                                ExceptionCodes.USER_NOT_FOUND
                        )
                );

        return new User(
                userEntity.getUsername(),
                userEntity.getPasswordHash(),
                Collections.singletonList(
                        new SimpleGrantedAuthority(
                                "ROLE_" + userEntity.getRole().toString()
                        )
                )
        );
    }
}
