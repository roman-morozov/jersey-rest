package com.epam.grow.model;

import com.epam.grow.core.service.api.dto.PurchaseItemRequestDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Purchase {
    private List<PurchaseItemRequestDto> purchaseItems = new ArrayList<>();

    public void addItem(PurchaseItemRequestDto purchaseItem) {
        purchaseItems.add(purchaseItem);
    }
}
