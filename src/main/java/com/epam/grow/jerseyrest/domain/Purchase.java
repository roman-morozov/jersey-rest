package com.epam.grow.jerseyrest.domain;

import lombok.*;
import lombok.experimental.Delegate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class Purchase extends AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private List<PurchaseItem> items = new ArrayList<>();
    private LocalDateTime purchaseDate;
    private BigDecimal cost = BigDecimal.ZERO;

    public void addItem(@NonNull PurchaseItem item) {
        items.add(item);
    }

    public void removeItem(@NonNull PurchaseItem item) {
        items.remove(item);
    }
}
