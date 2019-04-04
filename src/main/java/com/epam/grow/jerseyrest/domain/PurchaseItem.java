package com.epam.grow.jerseyrest.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
public class PurchaseItem {
    @ManyToOne
    private Purchase purchase;
    @ManyToOne
    private Item item;
    private BigDecimal amount;
}
