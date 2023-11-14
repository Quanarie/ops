package com.ops.ops.persistence.repositories;

import com.ops.ops.persistence.entities.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<OfferEntity, Long> {
}
