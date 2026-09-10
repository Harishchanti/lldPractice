package org.practice.vendingMachine;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Slot {
    int slotId;
    Product product;
    AtomicInteger quantity;

    public boolean isAvailable() {
        return quantity.get() > 0;
    }

    public void reduce() {
        if (quantity.get() <= 0) {
            throw new RuntimeException("Out of stock");
        }
        quantity.decrementAndGet();
    }
}
