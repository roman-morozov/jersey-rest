package com.epam.grow.core.service.api.mapper;

import com.epam.grow.core.domain.Purchase;
import com.epam.grow.core.service.api.dto.PurchaseDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = PurchaseItemMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface PurchaseMapper {
    @Mapping(source = "cost", target = "totalCost")
    PurchaseDto convertToDto(Purchase purchase);
}
