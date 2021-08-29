package com.koushik.parkmanager;

import com.koushik.parkmanager.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class ParkmanagerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ParkmanagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final String parkingLotName = "Havoc Parking Lot";
        Address address = Address.builder().block("West Block").street("Baker Street").city("Winterfell").state("Westeros").country("India").build();

        Map<ParkingSpotType, List<ParkingSpot>> parkingSlots = new HashMap<>();

        List<ParkingSpot> smallSlotsList = new ArrayList<>();
        smallSlotsList.add(ParkingSpot.builder().name("S1").parkingSpotType(ParkingSpotType.SMALL).build());
//        smallSlotsList.add(ParkingSpot.builder().name("S2").parkingSpotType(ParkingSpotType.SMALL).build());
//        smallSlotsList.add(ParkingSpot.builder().name("S3").parkingSpotType(ParkingSpotType.SMALL).build());
//        smallSlotsList.add(ParkingSpot.builder().name("S4").parkingSpotType(ParkingSpotType.SMALL).build());
//        smallSlotsList.add(ParkingSpot.builder().name("S5").parkingSpotType(ParkingSpotType.SMALL).build());

        List<ParkingSpot> compactSlotsList = new ArrayList<>();
        compactSlotsList.add(ParkingSpot.builder().name("C1").parkingSpotType(ParkingSpotType.COMPACT).build());
        compactSlotsList.add(ParkingSpot.builder().name("C2").parkingSpotType(ParkingSpotType.COMPACT).build());
        compactSlotsList.add(ParkingSpot.builder().name("C3").parkingSpotType(ParkingSpotType.COMPACT).build());
        compactSlotsList.add(ParkingSpot.builder().name("C4").parkingSpotType(ParkingSpotType.COMPACT).build());
        compactSlotsList.add(ParkingSpot.builder().name("C5").parkingSpotType(ParkingSpotType.COMPACT).build());

        List<ParkingSpot> largeSlotsList = new ArrayList<>();
        largeSlotsList.add(ParkingSpot.builder().name("L1").parkingSpotType(ParkingSpotType.LARGE).build());
        largeSlotsList.add(ParkingSpot.builder().name("L2").parkingSpotType(ParkingSpotType.LARGE).build());
        largeSlotsList.add(ParkingSpot.builder().name("L3").parkingSpotType(ParkingSpotType.LARGE).build());
        largeSlotsList.add(ParkingSpot.builder().name("L4").parkingSpotType(ParkingSpotType.LARGE).build());
        largeSlotsList.add(ParkingSpot.builder().name("L5").parkingSpotType(ParkingSpotType.LARGE).build());


        parkingSlots.put(ParkingSpotType.SMALL,smallSlotsList);
        parkingSlots.put(ParkingSpotType.COMPACT,compactSlotsList);
        parkingSlots.put(ParkingSpotType.LARGE,largeSlotsList);

        List<ParkingFloor> parkingFloorsList = new ArrayList<>();

        ParkingFloor parkingFloor1 = ParkingFloor.builder().name("G0").parkingSlots(parkingSlots).build();
        ParkingFloor parkingFloor2 = ParkingFloor.builder().name("G1").parkingSlots(parkingSlots).build();
        ParkingFloor parkingFloor3 = ParkingFloor.builder().name("G2").parkingSlots(parkingSlots).build();

        parkingFloorsList.add(parkingFloor1);
        parkingFloorsList.add(parkingFloor2);
        parkingFloorsList.add(parkingFloor3);

        ParkingLot parkingLot = ParkingLot.getInstance(parkingLotName,address,parkingFloorsList);

        VehicleDetails vehicleDetails1 = VehicleDetails.builder().vehicleType(VehicleType.BIKE).licenceNumber("WS-21-WF-2898").build();
        VehicleDetails vehicleDetails2 = VehicleDetails.builder().vehicleType(VehicleType.BIKE).licenceNumber("WS-19-KL-9898").build();
        VehicleDetails vehicleDetails3 = VehicleDetails.builder().vehicleType(VehicleType.BUS).licenceNumber("WS-09-WF-1452").build();

        TicketDetails ticketDetails1 = parkingLot.assignTicket(vehicleDetails1);
        TicketDetails ticketDetails2 = parkingLot.assignTicket(vehicleDetails2);
        log.info("Ticket details :{}",ticketDetails1.toString());
        log.info("Ticket details :{}",ticketDetails2.toString());
        Thread.sleep(10000);
        log.info("Parking price: {}",parkingLot.scanAndPay(ticketDetails1));
        Thread.sleep(10000);
        log.info("Parking price: {}",parkingLot.scanAndPay(ticketDetails2));

        TicketDetails ticketDetails3 = parkingLot.assignTicket(vehicleDetails3);
        log.info("Ticket details :{}",ticketDetails3.toString());
        Thread.sleep(10000);
        log.info("Parking price: {}",parkingLot.scanAndPay(ticketDetails3));

    }
}
