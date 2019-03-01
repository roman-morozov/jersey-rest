package com.epam.grow.jerseyrest.domain;

import lombok.*;
import lombok.experimental.Delegate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Purchase extends AbstractEntity {
    @ManyToMany(fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private List<Item> items = new ArrayList<>();
    private LocalDateTime purchaseDate;
    private BigDecimal cost;

    public void addItem(@NonNull Item item) {
        items.add(item);
    }

    public void removeItem(@NonNull Item item) {
        items.remove(item);
    }
}
