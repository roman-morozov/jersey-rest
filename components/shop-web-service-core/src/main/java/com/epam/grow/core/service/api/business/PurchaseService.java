package com.epam.grow.core.service.api.business;

import com.epam.grow.core.service.api.dto.PurchaseDto;
import com.epam.grow.core.service.api.dto.PurchaseItemRequestDto;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    List<PurchaseDto> getAllPurchases();
    Optional<PurchaseDto> getPurchaseById(Long id);
    PurchaseDto makePurchase(List<PurchaseItemRequestDto> item);
}
