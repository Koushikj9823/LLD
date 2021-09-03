package com.koushik.parkmanager.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class VehicleDetails {
    private String licenceNumber;
    private VehicleType vehicleType;
    private TicketDetails ticket;
    /*
    PaymentInfo info -> using ticket
     */
}
