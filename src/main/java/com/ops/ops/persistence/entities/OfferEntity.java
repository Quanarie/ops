package com.ops.ops.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "offers")
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    private Long id;

    private UUID uuid;

    private String title;

    private Float price;

    @PrePersist
    public void initializeUUID() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
