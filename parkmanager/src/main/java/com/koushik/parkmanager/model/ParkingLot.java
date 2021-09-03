package com.koushik.parkmanager.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
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
//    private List<Entrance> entranceList;
//    private List<Exit> exitList;

    private static ParkingLot parkingLot = null;


//boolean isParkingSpaceAvailable(Vehicle vehicle)
//boolean updateParkingAttendent()

    /*
        Gate class{
            int gateId;
            ParkingAttendent;

        }
        Entrance extends Gate {
            createTicket: method
        }
        Exit extends Gate{
            payForTicket: method
        }
     */
    public static ParkingLot getInstance(String name, Address address, List<ParkingFloor> parkingFloors){
        if(parkingLot==null)
            return new ParkingLotBuilder().name(name).address(address).floorsList(parkingFloors).build();
        return parkingLot;
    }

    public void addParkingFloors(String name, Map<ParkingSpotType, HashMap<String,ParkingSpot>> parkingSpotTypeListMap){
        ParkingFloor parkingFloor = new ParkingFloor.ParkingFloorBuilder().name(name).parkingSlots(parkingSpotTypeListMap).build();
        floorsList.add(parkingFloor);
    }

    public void removeParkingFloors(ParkingFloor parkingFloor){
        floorsList.remove(parkingFloor);
    }

    //will be moved to entrance
    public TicketDetails assignTicket(VehicleDetails vehicleDetails){

        ParkingSpot parkingSpot = getParkingSpot(vehicleDetails);
        if(parkingSpot==null)
            return null;
        return createTicketDetails(vehicleDetails,parkingSpot);
    }

    private TicketDetails createTicketDetails(VehicleDetails vehicleDetails, ParkingSpot parkingSpot) {
        return TicketDetails.createTicket(vehicleDetails, parkingSpot);
    }

    //will be moved to exit
    public double scanAndPay(TicketDetails ticketDetails){
        if(ticketDetails==null)
            return 0.0;
        long endTime = System.currentTimeMillis();
        ticketDetails.getParkingSpot().removeVehicle(ticketDetails.getVehicleDetails());
        int duration = (int) (endTime - ticketDetails.getStartTime());
        return ticketDetails.getParkingSpot().getParkingSpotType().getPrice(duration);
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
