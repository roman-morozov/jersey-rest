package com.epam.grow.core.service.api.mapper;

import com.epam.grow.core.domain.PurchaseItem;
import com.epam.grow.core.service.api.dto.PurchaseItemDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.RoundingMode;

@Mapper(uses = ItemMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface PurchaseItemMapper {

    @Mapping(target = "id", source = "item.id")
    @Mapping(target = "name", source = "item.name")
    @Mapping(target = "description", source = "item.description")
    @Mapping(target = "price", source = "item.price", numberFormat = "#,###.00")
    @Mapping(target = "cost", source = "purchaseItem")
    PurchaseItemDto convertToDto(PurchaseItem purchaseItem);

    default String purchaseItemToCost(PurchaseItem purchaseItem) {
        return purchaseItem.getAmount()
                .multiply(purchaseItem.getItem().getPrice())
                .setScale(2, RoundingMode.HALF_UP)
                .toPlainString();
    }
}
