package com.epam.grow.jerseyrest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Item extends AbstractEntity {
    private String name;
    private String description;
    private BigDecimal price;
}
