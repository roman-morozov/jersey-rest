package com.epam.grow.jerseyrest.service.api;

import com.epam.grow.jerseyrest.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> getAllItems();
    Optional<Item> getItemById(Long id);
    Item save(Item item);
    void delete(Long id);
    boolean exists(Long id);
}
