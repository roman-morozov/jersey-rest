package com.epam.grow.jerseyrest.service.api.business;

import com.epam.grow.jerseyrest.service.api.dto.ItemDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<ItemDto> getAllItems();
    Optional<ItemDto> getItemById(Long id);
    ItemDto createItem(ItemDto item);
    ItemDto updateItem(Long id, ItemDto item);
    void delete(Long id);
}