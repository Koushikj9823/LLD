package com.koushik.parkmanager.model;

import lombok.*;
import org.springframework.data.annotation.Id;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParkingSpot {
    @Id
    private Integer id;
    private String name;
    @Builder.Default
    private boolean available = true;
    private String floorName;
    private VehicleDetails vehicleDetails;
    private ParkingSpotType parkingSpotType;

    public ParkingSpot(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }

    protected void addVehicle(VehicleDetails vehicleDetails){
        this.vehicleDetails = vehicleDetails;
        this.available = false;
    }
    protected void removeVehicle(VehicleDetails vehicleDetails){
        this.vehicleDetails = null;
        this.available = true;
    }
    public double getPrice(long duration) {
        System.out.println("Default Price...");
        return duration*0.01;
    }
}
