package com.ops.ops.persistence.entities;

import com.ops.ops.rest.dto.customer.CustomerRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    private Long id;

    @Column(unique = true)
    private String username;

    private String passwordHash;

    private String name;

    private String address;

    private String phoneNumber;

    private CustomerRole role;

}
