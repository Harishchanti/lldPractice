package org.practice.parkingLotV2;

import java.util.List;
import java.util.Map;

/*
(Main Orchestrator)
Singleton Pattern (ParkingLot)

 */
public class ParkingLot {

    private ParkingLot() {
    }

    static ParkingLot parkingLot = new ParkingLot();

    static ParkingLot getInstance() {
        return parkingLot;
    }

    List<ParkingFloor> floors;
    Map<String, Ticket> activeTickets;

    synchronized Ticket parkVehicle(Vehicle v) {
        for (ParkingFloor floor : floors) {
            ParkingSlot slot = floor.getAvailableSlot(v.type);
            if (slot != null && slot.park(v)) {
                Ticket ticket = new Ticket(v, slot);
                activeTickets.put(v.number, ticket);
                return ticket;
            }
        }
        throw new RuntimeException("Parking Full");
    }

    synchronized void unparkVehicle(String vehicleNumber) {
        Ticket ticket = activeTickets.get(vehicleNumber);
        ticket.exitTime = System.currentTimeMillis();
        ticket.slot.unpark();
        activeTickets.remove(vehicleNumber);
    }

    public ParkingFloor getAvailableFloor() {
        return floors.getFirst();
    }
}
