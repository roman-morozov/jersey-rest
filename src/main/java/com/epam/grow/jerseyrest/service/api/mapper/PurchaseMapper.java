package com.epam.grow.jerseyrest.service.api.mapper;

import com.epam.grow.jerseyrest.domain.Purchase;
import com.epam.grow.jerseyrest.service.api.dto.PurchaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PurchaseMapper {
    PurchaseDto convertToDto(Purchase purchase);

    @Mapping(target = "id", ignore = true)
    Purchase convertToEntity(PurchaseDto purchaseDto);
}
