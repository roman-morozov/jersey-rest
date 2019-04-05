package com.epam.grow.core.service.api.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PurchaseItemRequestDto {
	@NotNull
	private Long id;

	@NotNull
	private String amount;
}
