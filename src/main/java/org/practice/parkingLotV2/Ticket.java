package org.practice.parkingLotV2;

import java.util.UUID;

public class Ticket {
    String id;
    Vehicle vehicle;
    ParkingSlot slot;
    long entryTime;
    long exitTime;

    public Ticket(Vehicle vehicle, ParkingSlot parkingSlot) {
        this.id = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.slot = parkingSlot;
        entryTime = System.currentTimeMillis();
    }

    double calculateFee(PricingStrategy pricing) {
        long duration = exitTime - entryTime;
        PricingStrategy pricingStrategy = new HourlyPricing();
        return pricingStrategy.calculate(duration);
    }
}
