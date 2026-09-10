package org.practice.vendingMachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Integer, Slot> slots = new HashMap<>();

    private Inventory() {
    }

    static Inventory inventory = new Inventory();

    static Inventory getInstance() {
        return inventory;
    }

    public Map<Integer, Slot> getSlots() {
        return slots;
    }

    public void addSlot(int code, Slot slot) {
        slots.put(code, slot);
    }

    public Slot getSlot(int code) {
        return slots.get(code);
    }
}
