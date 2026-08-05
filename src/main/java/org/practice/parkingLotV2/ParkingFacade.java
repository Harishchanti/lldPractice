package org.practice.parkingLotV2;

public class ParkingFacade {
    private SlotAllocationStrategy strategy;
    private PricingStrategy pricing;
    private ParkingLot lot;

    public Ticket park(Vehicle v) {
        ParkingFloor floor = lot.getAvailableFloor();
        ParkingSlot slot = strategy.allocate(v, floor);

        slot.park(v);
        Ticket ticket = new Ticket(v,slot);

        return ticket;
    }

    public double unpark(Ticket ticket) {
        ticket.exitTime = System.currentTimeMillis();

        ticket.slot.unpark();

        return ticket.calculateFee(pricing);
    }
}
