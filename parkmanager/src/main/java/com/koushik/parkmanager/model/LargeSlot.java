package com.koushik.parkmanager.model;

public class LargeSlot extends ParkingSpot {
    public LargeSlot(ParkingSpotType parkingSpotType) {
        super(parkingSpotType);
    }

    @Override
    public double getPrice(long duration) {
        System.out.println(getName());
        System.out.println("Large Slot Price...");
        return duration*0.10;
    }
}
