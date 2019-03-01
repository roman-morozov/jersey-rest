package com.epam.grow.jerseyrest.controller.mapper;

import com.epam.grow.jerseyrest.controller.dto.ItemDto;
import com.epam.grow.jerseyrest.domain.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ItemMapper {
    @Mapping(source = "price", target = "price", numberFormat = "$#,###.00")
    ItemDto convertToDto(Item item);
    Item convertToEntity(ItemDto itemDto);
}
