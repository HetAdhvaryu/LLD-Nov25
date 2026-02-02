package advancedConcepts.projects.parkingLot.service.spotAllocationStrategy;

import advancedConcepts.projects.parkingLot.model.ParkingLot;
import advancedConcepts.projects.parkingLot.model.ParkingSpot;
import advancedConcepts.projects.parkingLot.model.Vehicle;

public class NearEntryExitSpotAllocationStrategy implements SpotAllocationStrategy{
    @Override
    public ParkingSpot getSpotForVehicle(ParkingLot parkingLot, Vehicle vehicle) {
        return null;
    }
}
