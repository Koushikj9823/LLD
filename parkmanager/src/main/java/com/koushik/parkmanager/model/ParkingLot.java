package com.koushik.parkmanager.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;


@Builder
@Getter
@Setter
@ToString
public class ParkingLot {

    private String name;
    private Address address;
    private List<ParkingFloor> floorsList;
    private static ParkingLot parkingLot = null;

    public static ParkingLot getInstance(String name, Address address, List<ParkingFloor> parkingFloors){
        if(parkingLot==null)
            return new ParkingLotBuilder().name(name).address(address).floorsList(parkingFloors).build();
        return parkingLot;
    }

    public void addParkingFloors(String name, Map<ParkingSpotType, List<ParkingSpot>> parkingSpotTypeListMap){
        ParkingFloor parkingFloor = new ParkingFloor.ParkingFloorBuilder().name(name).parkingSlots(parkingSpotTypeListMap).build();
        floorsList.add(parkingFloor);
    }

    public void removeParkingFloors(ParkingFloor parkingFloor){
        floorsList.remove(parkingFloor);
    }

    public TicketDetails assignTicket(VehicleDetails vehicleDetails){

        ParkingSpot parkingSpot = getParkingSpot(vehicleDetails);
        if(parkingSpot==null)
            return null;
        return createTicketDetails(vehicleDetails,parkingSpot);
    }

    private TicketDetails createTicketDetails(VehicleDetails vehicleDetails, ParkingSpot parkingSpot) {
        return TicketDetails.createTicket(vehicleDetails, parkingSpot);
    }

    public double scanAndPay(TicketDetails ticketDetails){
        long endTime = System.currentTimeMillis();
        ticketDetails.getParkingSpot().removeVehicle(ticketDetails.getVehicleDetails());
        int duration = (int) (endTime - ticketDetails.getStartTime());
        return ticketDetails.getParkingSpot().getPrice(duration);
    }
    private ParkingSpot getParkingSpot(VehicleDetails vehicleDetails){
        ParkingSpot parkingSpot = null;

        for (ParkingFloor floor :
                floorsList) {
            parkingSpot = floor.getRelevantParkingSpot(vehicleDetails);
            if(parkingSpot!=null) {
                parkingSpot.setFloorName(floor.getName());
                break;
            }
        }
        return parkingSpot;
    }
}
