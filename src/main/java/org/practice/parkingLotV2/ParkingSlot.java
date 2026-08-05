package org.practice.parkingLotV2;

enum SlotType {
    SMALL, MEDIUM, LARGE
}
enum VehicleType {
    BIKE, CAR, TRUCK
}

class Vehicle {
    String number;
    VehicleType type;
}

public class ParkingSlot {

    int id;
    SlotType type;
    boolean isFree;
    Vehicle vehicle;

    synchronized boolean park(Vehicle v) {
        if (!isFree) return false;
        this.vehicle = v;
        this.isFree = false;
        return true;
    }

    synchronized void unpark() {
        this.vehicle = null;
        this.isFree = true;
    }
}
