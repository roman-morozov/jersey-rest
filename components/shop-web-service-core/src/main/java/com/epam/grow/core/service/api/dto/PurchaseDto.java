package com.epam.grow.core.service.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PurchaseDto implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private List<PurchaseItemDto> items;
    @NotNull
    private LocalDateTime purchaseDate;
    @NotNull
    private String totalCost;
}
