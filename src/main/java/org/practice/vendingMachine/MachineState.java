package org.practice.vendingMachine;

public enum MachineState {
    IDLE,
    ACCEPTING_PAYMENT,
    ITEM_SELECTED,
    DISPENSING,
    OUT_OF_STOCK,
    REFUNDING
}
