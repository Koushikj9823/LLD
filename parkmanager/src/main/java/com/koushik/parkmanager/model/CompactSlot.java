package com.koushik.parkmanager.model;

public class CompactSlot extends ParkingSpot {
    public CompactSlot(ParkingSpotType parkingSpotType) {
        super(parkingSpotType);
    }

   @Override
    public double getPrice(long duration) {

        System.out.println("Compact Slot Price...");
        return duration*0.075;
    }
}
