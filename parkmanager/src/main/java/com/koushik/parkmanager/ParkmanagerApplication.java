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

        Map<ParkingSpotType, HashMap<String,ParkingSpot>> parkingSlots = new HashMap<>();


        HashMap<String,ParkingSpot> smallMap = new HashMap<>();
        smallMap.put("S01",ParkingSpot.builder().name("S01").parkingSpotType(ParkingSpotType.SMALL).build());
        smallMap.put("S02",ParkingSpot.builder().name("S02").parkingSpotType(ParkingSpotType.SMALL).build());
        smallMap.put("S11",ParkingSpot.builder().name("S11").parkingSpotType(ParkingSpotType.SMALL).build());
        smallMap.put("S12",ParkingSpot.builder().name("S12").parkingSpotType(ParkingSpotType.SMALL).build());
        smallMap.put("S21",ParkingSpot.builder().name("S21").parkingSpotType(ParkingSpotType.SMALL).build());

        HashMap<String,ParkingSpot> compactMap = new HashMap<>();
        compactMap.put("C01",ParkingSpot.builder().name("C01").parkingSpotType(ParkingSpotType.COMPACT).build());
        compactMap.put("C02",ParkingSpot.builder().name("C02").parkingSpotType(ParkingSpotType.COMPACT).build());
        compactMap.put("C11",ParkingSpot.builder().name("C11").parkingSpotType(ParkingSpotType.COMPACT).build());
        compactMap.put("C12",ParkingSpot.builder().name("C12").parkingSpotType(ParkingSpotType.COMPACT).build());
        compactMap.put("C21",ParkingSpot.builder().name("C21").parkingSpotType(ParkingSpotType.COMPACT).build());

        HashMap<String,ParkingSpot> largeMap = new HashMap<>();
        largeMap.put("L01",ParkingSpot.builder().name("L01").parkingSpotType(ParkingSpotType.LARGE).build());
        largeMap.put("L02",ParkingSpot.builder().name("L02").parkingSpotType(ParkingSpotType.LARGE).build());


        parkingSlots.put(ParkingSpotType.SMALL,smallMap);
        parkingSlots.put(ParkingSpotType.COMPACT,compactMap);
        parkingSlots.put(ParkingSpotType.LARGE,largeMap);

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
        log.info("Ticket details :{}",ticketDetails1.toString());
        Thread.sleep(10000);
        TicketDetails ticketDetails2 = parkingLot.assignTicket(vehicleDetails2);
        if(ticketDetails2==null)
            log.info("Sorry No Parking Space Available");
        else
            log.info("Ticket details :{}",ticketDetails2.toString());

        log.info("Parking price: {}",parkingLot.scanAndPay(ticketDetails1));
        Thread.sleep(10000);
        log.info("Parking price: {}",parkingLot.scanAndPay(ticketDetails2));

        TicketDetails ticketDetails3 = parkingLot.assignTicket(vehicleDetails3);
        log.info("Ticket details :{}",ticketDetails3.toString());
        Thread.sleep(10000);
        log.info("Parking price: {} for ticket number {}",parkingLot.scanAndPay(ticketDetails3),ticketDetails3.getTicketNumber());

    }
}
