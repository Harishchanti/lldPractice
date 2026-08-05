package org.practice.parkingLot;

import java.util.List;
import java.util.Map;

public class ParkingLot {

    Map<VehicleType, List<ParkingSlot>> parkingSlotMap;

    static ParkingLot PARKINGLOT_INSTANCE = new ParkingLot();

    private ParkingLot() {

    }

    static ParkingLot getInstance() {
        return PARKINGLOT_INSTANCE;
    }

    public ParkingSlot getAvailablePartingSlot(Vehicle vehicle) {
        List<ParkingSlot> parkingSlotList =
                parkingSlotMap.get(vehicle.vehicleType);
        for (ParkingSlot slot : parkingSlotList) {
            if (slot.canFitTheVehicle(vehicle)) {
                slot.parkVehicle(vehicle);
                return slot;
            }
        }
        System.out.println("No parking slot is available");
        return null;
    }

    // CRUD Operations on the Parking lot
    void addParingSpot(int floorNum, ParkingSlotType parkingSlotType) {

    }

    void removeParingSpot() {

    }

}
