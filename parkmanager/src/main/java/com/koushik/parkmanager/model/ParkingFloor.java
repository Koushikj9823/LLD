package com.koushik.parkmanager.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

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
    private Map<ParkingSpotType, List<ParkingSpot>> parkingSlots;

    public ParkingSpot getRelevantParkingSpot(VehicleDetails vehicleDetails){

        VehicleType vehicleType = vehicleDetails.getVehicleType();
        ParkingSpotType parkingSpotType = getCorrectSpot(vehicleType);

        List<ParkingSpot> parkingSpotList = parkingSlots.get(parkingSpotType);
        ParkingSpot assignedParkingSpot = null;
        for (ParkingSpot spot :
                parkingSpotList) {

            if(spot.isAvailable()){
                assignedParkingSpot = getCorrectSpotInstance(parkingSpotType);
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
        log.info("Reached here...");
        return null;
    }
    private ParkingSpot getCorrectSpotInstance(ParkingSpotType parkingSpotType){

        log.info("Vehicle type: {}",parkingSpotType);
        if(parkingSpotType.equals(ParkingSpotType.SMALL))
            return new TwoWheelerSlot(ParkingSpotType.SMALL);
        else if(parkingSpotType.equals(ParkingSpotType.COMPACT))
            return new CompactSlot(ParkingSpotType.COMPACT);
        else if(parkingSpotType.equals(ParkingSpotType.LARGE))
            return new LargeSlot(ParkingSpotType.LARGE);
        else if(parkingSpotType.equals(ParkingSpotType.HANDICAPPED))
            return new LargeSlot(ParkingSpotType.HANDICAPPED);
        else if(parkingSpotType.equals(ParkingSpotType.ELECTRIC))
            return new LargeSlot(ParkingSpotType.ELECTRIC);
        log.info("Reached here...");
        return new ParkingSpot();
    }




}
