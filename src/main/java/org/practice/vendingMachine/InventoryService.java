package org.practice.vendingMachine;



public class InventoryService {

    Inventory inventory;

    InventoryService() {
        this.inventory = Inventory.getInstance();
    }

    public Slot getSlot(int slotId) {
        return inventory.getSlot(slotId);
    }
}
