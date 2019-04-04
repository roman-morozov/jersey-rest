package com.epam.grow.jerseyrest.repository;

import com.epam.grow.jerseyrest.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
