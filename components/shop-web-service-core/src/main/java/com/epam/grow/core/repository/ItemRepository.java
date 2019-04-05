package com.epam.grow.core.repository;

import com.epam.grow.core.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}