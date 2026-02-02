package advancedConcepts.projects.parkingLot.service;

import advancedConcepts.projects.parkingLot.model.Gate;
import advancedConcepts.projects.parkingLot.model.ParkingFloor;
import advancedConcepts.projects.parkingLot.model.ParkingLot;
import advancedConcepts.projects.parkingLot.model.ParkingSpot;
import advancedConcepts.projects.parkingLot.model.constants.*;
import advancedConcepts.projects.parkingLot.repository.*;

import java.util.ArrayList;
import java.util.List;

public class InitializationService {
    private GateRepository gateRepository;
    private ParkingSpotRepository parkingSpotRepository;
    private ParkingLotRepository parkingLotRepository;
    private ParkingFloorRepository parkingFloorRepository;
    private OperatorRepository operatorRepository;

    public InitializationService() {
        this.gateRepository = new GateRepository();
        this.parkingSpotRepository = new ParkingSpotRepository();
        this.parkingLotRepository = new ParkingLotRepository();
        this.parkingFloorRepository = new ParkingFloorRepository();
        this.operatorRepository = new OperatorRepository();
    }

    public void init(){
        System.out.println("***** Starting Initialization *****");

        //Creating a parking Lot object
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Radhe Parking Lot");
        parkingLot.setAddress("Somewhere Inside an expensive mall, that we only go for window shopping");
        parkingLot.setParkingLotStatus(ParkingLotStatus.AVAILABLE);
        parkingLot.setCapacity(100);
        parkingLot.setVehicleTypesSupported(List.of(VehicleType.TWO_WHEELER, VehicleType.FOUR_WHEELER));
        parkingLot = parkingLotRepository.save(parkingLot);

        //adding floors and spots to the parkingLot
        for(int i=1;i<=10;i++){
            ParkingFloor parkingFloor = new ParkingFloor();
            parkingFloor.setFloorNumber(i);
            parkingFloor.setParkingFloorStatus(ParkingFloorStatus.AVAILABLE);
            List<ParkingSpot> parkingSpots = new ArrayList<>();
            for(int j=1;j<=10;j++){
                ParkingSpot parkingSpot = new ParkingSpot();
                parkingSpot.setNumber(i + "-" + j);
                parkingSpot.setParkingSpotType(ParkingSpotType.NORMAL);
                parkingSpot.setVehicleType(VehicleType.FOUR_WHEELER);
                parkingSpot.setParkingSpotStatus(ParkingSpotStatus.FREE);
                parkingSpot = parkingSpotRepository.save(parkingSpot);
                parkingSpots.add(parkingSpot);
            }

            //creating an entry gate
            Gate entryGate = new Gate();
            entryGate.setGateType(GateType.ENTRY);
            entryGate.setGateStatus(GateStatus.OPEN);
            //TODO: Add operator
            entryGate = gateRepository.save(entryGate);

            //creating an exit gate
            Gate exitGate = new Gate();
            exitGate.setGateType(GateType.EXIT);
            exitGate.setGateStatus(GateStatus.OPEN);
            //TODO: Add operator
            exitGate = gateRepository.save(exitGate);

            List<Gate> gates = new ArrayList<>();
            gates.add(entryGate);
            gates.add(exitGate);

            parkingFloor.setParkingSpots(parkingSpots);
            parkingFloor.setGates(gates);

            parkingFloorRepository.save(parkingFloor);
        }
    }
}
