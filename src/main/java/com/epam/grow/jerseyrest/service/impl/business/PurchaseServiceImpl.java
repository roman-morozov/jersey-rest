package com.epam.grow.jerseyrest.service.impl.business;

import com.epam.grow.jerseyrest.domain.Item;
import com.epam.grow.jerseyrest.domain.Purchase;
import com.epam.grow.jerseyrest.repository.PurchaseRepository;
import com.epam.grow.jerseyrest.service.api.business.PurchaseService;
import com.epam.grow.jerseyrest.service.api.dto.PurchaseDto;
import com.epam.grow.jerseyrest.service.api.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepository;
    private PurchaseMapper purchaseMapper;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository,
                               PurchaseMapper purchaseMapper) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseMapper = purchaseMapper;
    }

    @Transactional
    @Override
    public List<PurchaseDto> getAllPurchases() {
        return purchaseRepository.findAll().stream().map(purchaseMapper::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<PurchaseDto> getPurchaseById(Long id) {
        return purchaseRepository.findById(id).map(purchaseMapper::convertToDto);
    }

    @Transactional
    @Override
    public PurchaseDto makePurchase(PurchaseDto purchaseDto) {
        Purchase purchase = purchaseMapper.convertToEntity(purchaseDto);
        Purchase result = purchaseRepository.save(purchase);
        return purchaseMapper.convertToDto(result);
    }
}
