package com.epam.grow.core.domain;

import lombok.*;
import lombok.experimental.Delegate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class Purchase extends AbstractEntity {

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<PurchaseItem> items = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime purchaseDate;

    @Column(nullable = false)
    private BigDecimal cost = BigDecimal.ZERO;

    public void addItem(@NonNull PurchaseItem item) {
        items.add(item);
        item.setPurchase(this);
        cost = cost.add(item.getAmount().multiply(item.getItem().getPrice()));
    }

    public void removeItem(@NonNull PurchaseItem item) {
        if (items.remove(item)) {
            cost = cost.subtract(item.getAmount().multiply(item.getItem().getPrice()));
            item.setPurchase(null);
        }
    }
}
