package org.practice.vendingMachine;

public class Money {
    private final int amount; // in paise

    public Money(int amount) {
        this.amount = amount;
    }

    public Money add(Money other) {
        return new Money(this.amount + other.amount);
    }

    public boolean isGreaterOrEqual(Money other) {
        return this.amount >= other.amount;
    }
}
