package com.ops.ops.persistence.repositories;

import com.ops.ops.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByUsername(String username);

    void deleteByUsername(String username);

}
