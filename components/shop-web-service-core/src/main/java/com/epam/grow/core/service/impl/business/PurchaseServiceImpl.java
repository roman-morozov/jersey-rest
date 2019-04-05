package com.epam.grow.core.service.impl.business;


import com.epam.grow.core.domain.Item;
import com.epam.grow.core.domain.Purchase;
import com.epam.grow.core.domain.PurchaseItem;
import com.epam.grow.core.repository.ItemRepository;
import com.epam.grow.core.repository.PurchaseRepository;
import com.epam.grow.core.service.api.business.PurchaseService;
import com.epam.grow.core.service.api.dto.PurchaseDto;
import com.epam.grow.core.service.api.dto.PurchaseItemDto;
import com.epam.grow.core.service.api.dto.PurchaseItemRequestDto;
import com.epam.grow.core.service.api.mapper.PurchaseItemMapper;
import com.epam.grow.core.service.api.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepository;
    private PurchaseMapper purchaseMapper;
    private PurchaseItemMapper purchaseItemMapper;
    private ItemRepository itemRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository,
                               PurchaseMapper purchaseMapper,
                               PurchaseItemMapper purchaseItemMapper,
                               ItemRepository itemRepository) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseMapper = purchaseMapper;
        this.purchaseItemMapper = purchaseItemMapper;
        this.itemRepository = itemRepository;
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
    public PurchaseDto makePurchase(List<PurchaseItemRequestDto> purchaseItems) {
        Purchase purchase = createPurchase(purchaseItems);
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setCost(calculateCost(purchase));
        Purchase result = purchaseRepository.save(purchase);
        return purchaseMapper.convertToDto(result);
    }

    private Purchase createPurchase(List<PurchaseItemRequestDto> purchaseItems) {
        Map<Long, String> itemsMap = purchaseItems.stream()
                .collect(Collectors.toMap(PurchaseItemRequestDto::getId, PurchaseItemRequestDto::getAmount));
        return itemRepository.findAllById(itemsMap.keySet())
                .stream()
                .map(item -> createPurchaseItem(itemsMap, item))
                .reduce(new Purchase(), (p, purchaseItem) -> {
                    p.addItem(purchaseItem);
                    return p;
                    }, (first, second) -> first);
    }

    private PurchaseItem createPurchaseItem(Map<Long, String> itemsMap, Item item) {
        BigDecimal amount = new BigDecimal(itemsMap.get(item.getId()))
                .setScale(2, RoundingMode.HALF_UP);
        return new PurchaseItem(item, amount);
    }

    private BigDecimal calculateCost(Purchase purchase) {
        return purchase.getItems().stream()
                .reduce(BigDecimal.ZERO, (cost, purchaseItem) -> {
                    BigDecimal purchaseItemCost = purchaseItem.getAmount().multiply(purchaseItem.getItem().getPrice());
                    return cost.add(purchaseItemCost);
                }, BigDecimal::add);
    }
}
