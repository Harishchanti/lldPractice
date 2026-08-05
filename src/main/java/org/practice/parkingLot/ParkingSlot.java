package org.practice.parkingLot;

public class ParkingSlot {
    Vehicle vehicle;
    ParkingSlotType parkingSlotType;
    boolean isFree;
    int floorNumber;

    public boolean isParkingSlotFree() {
        return vehicle == null;
    }

    synchronized void parkVehicle(Vehicle vehicle) {
        if (!isFree)
            return;
        this.vehicle = vehicle;
    }

    synchronized boolean canFitTheVehicle(Vehicle vehicle) {

        if (isParkingSlotFree()) {
            if (vehicle.vehicleType == VehicleType.CAR && parkingSlotType == ParkingSlotType.MEDIUM)
                return true;

            if (vehicle.vehicleType == VehicleType.BIKE && parkingSlotType == ParkingSlotType.SMALL)
                return true;

            return vehicle.vehicleType == VehicleType.TRUCK && parkingSlotType == ParkingSlotType.LARGE;
        }
        return false;
    }
}
