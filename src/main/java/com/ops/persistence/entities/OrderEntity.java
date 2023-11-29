package com.ops.persistence.entities;

import com.ops.rest.dto.order.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private OfferEntity offer;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private UUID uuid;

    @PrePersist
    public void initializeUUID() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }

}
