package com.epam.grow.controller;

import com.epam.grow.core.service.api.business.PurchaseService;
import com.epam.grow.core.service.api.dto.PurchaseDto;
import com.epam.grow.core.service.api.dto.PurchaseItemRequestDto;
import com.epam.grow.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public String getAllPurchases(Model model) {
        List<PurchaseDto> result = purchaseService.getAllPurchases();
        model.addAttribute("purchases", result);
        return "purchases";
    }

    @GetMapping(value = "/{id}")
    public String getPurchaseDetails(@PathVariable("id") Long id, Model model) {
        PurchaseDto result = purchaseService.getPurchaseById(id).orElse(null);
        model.addAttribute("purchase", result);
        return "purchaseDetails";
    }

    @PostMapping
    public String makePurchase(@ModelAttribute("purchase") Purchase purchase, Model model) {
        PurchaseDto result = purchaseService.makePurchase(purchase.getPurchaseItems());
        model.addAttribute("purchase", result);
        return "redirect:/purchases";
    }
}
