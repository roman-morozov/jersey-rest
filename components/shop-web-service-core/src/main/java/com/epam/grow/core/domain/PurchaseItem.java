package com.epam.grow.core.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class PurchaseItem {

    @EmbeddedId
    private PurchaseItemId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("purchaseId")
    private Purchase purchase;

    @Setter(AccessLevel.NONE)
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    private Item item;

    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false)
    private BigDecimal amount;

    public PurchaseItem(Item item, BigDecimal amount) {
        this.id = new PurchaseItemId(null, item.getId());
        this.item = item;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PurchaseItem that = (PurchaseItem) o;

        return Objects.equals(purchase, that.purchase) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchase, item);
    }
}
