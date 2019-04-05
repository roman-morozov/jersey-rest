package com.epam.grow.core.service.api.mapper;

import com.epam.grow.core.domain.Item;
import com.epam.grow.core.service.api.dto.ItemDto;
import com.epam.grow.core.service.api.dto.PurchaseItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    @Mapping(source = "price", target = "price", numberFormat = "#,###.00")
    ItemDto convertToDto(Item item);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "price", target = "price", numberFormat = "#,###.00")
    Item convertToEntity(ItemDto itemDto);
}
