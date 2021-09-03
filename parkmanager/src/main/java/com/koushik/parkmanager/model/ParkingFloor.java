package com.koushik.parkmanager.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
@Getter
@Setter
public class ParkingFloor {

    private String name;
    private Map<ParkingSpotType, HashMap<String,ParkingSpot>> parkingSlots;
    /*
       int levelId;
       DisplayBoard
     */

    public ParkingSpot getRelevantParkingSpot(VehicleDetails vehicleDetails){

        VehicleType vehicleType = vehicleDetails.getVehicleType();
        ParkingSpotType parkingSpotType = getCorrectSpot(vehicleType);

        HashMap<String, ParkingSpot> parkingSpotList = parkingSlots.get(parkingSpotType);
        ParkingSpot assignedParkingSpot = null;
        for(Map.Entry<String,ParkingSpot> m : parkingSpotList.entrySet()){
            if(m.getValue().isAvailable()) {
                assignedParkingSpot = m.getValue();
                assignedParkingSpot.addVehicle(vehicleDetails);
                log.info("Assigned parking Spot name {}",assignedParkingSpot.getName());
                break;
            }
        }
    return assignedParkingSpot;
    }

    private ParkingSpotType getCorrectSpot(VehicleType vehicleType){

        log.info("Vehicle type: {}",vehicleType);
        if(vehicleType.equals(VehicleType.BIKE))
            return ParkingSpotType.SMALL;
        else if(vehicleType.equals(VehicleType.AUTO))
            return ParkingSpotType.COMPACT;
        else if(vehicleType.equals(VehicleType.CAR))
            return ParkingSpotType.COMPACT;
        else if(vehicleType.equals(VehicleType.BUS))
            return ParkingSpotType.LARGE;
        return null;
    }





}
