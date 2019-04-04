package com.epam.grow.jerseyrest.service.impl.business;

import com.epam.grow.jerseyrest.service.api.dto.ItemDto;
import com.epam.grow.jerseyrest.service.api.mapper.ItemMapper;
import com.epam.grow.jerseyrest.domain.Item;
import com.epam.grow.jerseyrest.repository.ItemRepository;
import com.epam.grow.jerseyrest.service.api.business.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,
                           ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Transactional
    @Override
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream().map(itemMapper::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<ItemDto> getItemById(Long id) {
        return itemRepository.findById(id).map(itemMapper::convertToDto);
    }

    @Transactional
    @Override
    public ItemDto createItem(ItemDto itemDto) {
        Item item = itemMapper.convertToEntity(itemDto);
        return itemMapper.convertToDto(itemRepository.save(item));
    }

    @Transactional
    @Override
    public ItemDto updateItem(Long id, ItemDto itemDto) {
        if (itemRepository.existsById(id)) {
            Item item = itemMapper.convertToEntity(itemDto);
            item.setId(id);
            return itemMapper.convertToDto(itemRepository.save(item));
        } else {
            throw new NotFoundException("Item with id " + id + " not found");
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.delete(itemRepository.getOne(id));
        } else {
            throw new NotFoundException("Item with id " + id + " not found");
        }
    }
}
