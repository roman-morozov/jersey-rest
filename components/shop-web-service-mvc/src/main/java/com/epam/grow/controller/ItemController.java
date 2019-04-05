package com.epam.grow.controller;

import com.epam.grow.core.service.api.business.ItemService;
import com.epam.grow.core.service.api.dto.ItemDto;
import com.epam.grow.core.service.api.dto.PurchaseItemRequestDto;
import com.epam.grow.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping({"/", "/items"})
	public String getItems(Model model) {
		List<ItemDto> items = itemService.getAllItems();
		Purchase purchase = items.stream().reduce(new Purchase(), (p , item) -> {
					p.addItem(new PurchaseItemRequestDto());
					return p;
				}, (first, second) -> first);
		model.addAttribute("purchase", purchase);
		model.addAttribute("items", items);
		return "index";
	}

	@GetMapping("/items/add")
	public String addItemForm(Model model) {
		model.addAttribute("item", new ItemDto());
		return "addItemForm";
	}

	@PostMapping("/items")
	public String addItem(@ModelAttribute("item") @Valid ItemDto itemDto) {
		itemService.createItem(itemDto);
		return "redirect:/";
	}
}
