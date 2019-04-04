package com.epam.grow.jerseyrest.service.api.mapper;

import com.epam.grow.jerseyrest.service.api.dto.ItemDto;
import com.epam.grow.jerseyrest.domain.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ItemMapper {
    @Mapping(source = "price", target = "price", numberFormat = "#,###.00")
    ItemDto convertToDto(Item item);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "price", target = "price", numberFormat = "#,###.00")
    Item convertToEntity(ItemDto itemDto);
}
