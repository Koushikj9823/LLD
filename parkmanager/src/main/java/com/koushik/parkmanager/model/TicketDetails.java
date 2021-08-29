package com.koushik.parkmanager.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class TicketDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ticketNumber;
    private String floorName;
    private long startTime;
    private long endTime;
    private VehicleDetails vehicleDetails;
    private ParkingSpot parkingSpot;

    public static TicketDetails createTicket(VehicleDetails vehicleDetails,ParkingSpot parkingSpot){
        return new TicketDetailsBuilder()
                    .startTime(System.currentTimeMillis())
                    .floorName(parkingSpot.getFloorName())
                    .ticketNumber(vehicleDetails.getLicenceNumber()+"_"+System.currentTimeMillis())
                    .vehicleDetails(vehicleDetails).parkingSpot(parkingSpot)
                    .build();
    }


}
