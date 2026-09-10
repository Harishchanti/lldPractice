package org.practice.vendingMachine;

public class VendingMachine {
    private MachineState state;
    private InventoryService inventoryService;
    //private PaymentProcessor paymentProcessor;
    private Money currentBalance;
    private Slot selectedSlot;





    public synchronized void insertMoney(Money money) {

        //validateState(MachineState.IDLE, MachineState.ACCEPTING_PAYMENT);

        currentBalance = currentBalance.add(money);
        state = MachineState.ACCEPTING_PAYMENT;
    }

    public synchronized void selectItem(int slotId) {
        Slot slot = inventoryService.getSlot(slotId);

        if (!slot.isAvailable()) {
            state = MachineState.OUT_OF_STOCK;
            throw new RuntimeException("Out of stock");
        }

       /* if (!currentBalance.isGreaterOrEqual(slot.getProduct().getPrice())) {
            throw new RuntimeException("Insufficient funds");
        }*/

        this.selectedSlot = slot;
        state = MachineState.ITEM_SELECTED;
    }

    public synchronized void dispense() {
        validateState(MachineState.ITEM_SELECTED);

        state = MachineState.DISPENSING;

        selectedSlot.reduce(); // atomic

       //Money change = calculateChange();

        state = MachineState.IDLE;
       // reset();

        // async side effects
        emitEvent("ITEM_DISPENSED");
    }

    private void validateState(MachineState machineState) {
    }

    private void emitEvent(String itemDispensed) {

    }
}
