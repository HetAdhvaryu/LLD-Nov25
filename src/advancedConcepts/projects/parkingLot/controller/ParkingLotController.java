package advancedConcepts.projects.parkingLot.controller;

import advancedConcepts.projects.parkingLot.service.ParkingLotService;

public class ParkingLotController {

    private ParkingLotService parkingLotService;

    public ParkingLotController() {
        this.parkingLotService = new ParkingLotService();
    }
}
