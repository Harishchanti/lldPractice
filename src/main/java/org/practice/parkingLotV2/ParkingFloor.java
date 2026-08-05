package org.practice.parkingLotV2;

import java.util.List;
import java.util.Map;

public class ParkingFloor {
    int floorNumber;
    Map<SlotType, List<ParkingSlot>> slots;

    ParkingSlot getAvailableSlot(VehicleType type) {
        SlotType required = mapVehicleToSlot(type);

        for (ParkingSlot slot : slots.get(required)) {
            if (slot.isFree)
                return slot;
        }
        return null;
    }

    private SlotType mapVehicleToSlot(VehicleType type) {
        if (type == VehicleType.CAR)
            return SlotType.MEDIUM;
        if (type == VehicleType.BIKE)
            return SlotType.SMALL;
        if (type == VehicleType.TRUCK)
            return SlotType.LARGE;
        return null;
    }

    public ParkingSlot getNearestAvailableSlot(VehicleType type) {
        for (ParkingSlot parkingSlot : slots.get(mapVehicleToSlot(type))) {
            if (parkingSlot.isFree)
                return parkingSlot;
        }
        System.out.println("Parking is Full for vehicleType " + type);
        return null;
    }

    public ParkingSlot getRandomFreeSlot(VehicleType type) {
        for (ParkingSlot parkingSlot : slots.get(mapVehicleToSlot(type))) {
            if (parkingSlot.isFree)
                return parkingSlot;
        }
        System.out.println("Parking is Full for vehicleType " + type);
        return null;
    }
}
