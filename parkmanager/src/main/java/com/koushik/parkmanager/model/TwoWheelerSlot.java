package com.koushik.parkmanager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TwoWheelerSlot extends ParkingSpot {
    public TwoWheelerSlot(ParkingSpotType parkingSpotType) {
        super(parkingSpotType);
        System.out.println("Small Slot Constructor...");
    }
    @Override
    public double getPrice(long duration) {

        System.out.println("Small Slot Price...");
        System.out.println(getName());
        return duration*0.05;
    }
}
