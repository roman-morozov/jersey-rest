package com.epam.grow.jerseyrest.repository;

import com.epam.grow.jerseyrest.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
