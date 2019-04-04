package com.epam.grow.jerseyrest.service.api.business;

import com.epam.grow.jerseyrest.service.api.dto.PurchaseDto;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    List<PurchaseDto> getAllPurchases();
    Optional<PurchaseDto> getPurchaseById(Long id);
    PurchaseDto makePurchase(PurchaseDto item);
}
