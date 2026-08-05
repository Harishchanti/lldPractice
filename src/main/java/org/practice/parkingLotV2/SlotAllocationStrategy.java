package org.practice.parkingLotV2;

public interface SlotAllocationStrategy {
    ParkingSlot allocate(Vehicle v, ParkingFloor floor);
}

class NearestSlotStrategy implements SlotAllocationStrategy {
    public ParkingSlot allocate(Vehicle v, ParkingFloor floor) {
        return floor.getNearestAvailableSlot(v.type);
    }
}

class RandomSlotStrategy implements SlotAllocationStrategy {
    public ParkingSlot allocate(Vehicle v, ParkingFloor floor) {
        return floor.getRandomFreeSlot(v.type);
    }
}