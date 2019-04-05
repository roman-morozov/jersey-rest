package com.epam.grow.core.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class PurchaseItemId implements Serializable {

    private Long purchaseId;

    private Long itemId;

    public PurchaseItemId(Long purchaseId, Long itemId) {
        this.purchaseId = purchaseId;
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PurchaseItemId that = (PurchaseItemId) o;
        return Objects.equals(purchaseId, that.purchaseId) &&
                Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId, itemId);
    }
}
