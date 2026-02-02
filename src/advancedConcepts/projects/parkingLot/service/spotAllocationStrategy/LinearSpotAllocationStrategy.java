package advancedConcepts.projects.parkingLot.service.spotAllocationStrategy;

import advancedConcepts.projects.parkingLot.exception.NoParkingSpotAvailableException;
import advancedConcepts.projects.parkingLot.model.ParkingFloor;
import advancedConcepts.projects.parkingLot.model.ParkingLot;
import advancedConcepts.projects.parkingLot.model.ParkingSpot;
import advancedConcepts.projects.parkingLot.model.Vehicle;
import advancedConcepts.projects.parkingLot.model.constants.ParkingSpotStatus;

import java.util.List;

public class LinearSpotAllocationStrategy implements SpotAllocationStrategy {
    @Override
    public ParkingSpot getSpotForVehicle(ParkingLot parkingLot, Vehicle vehicle) {
        List<ParkingFloor> parkingFloors = parkingLot.getFloors();
        for(ParkingFloor floor : parkingFloors) {
            for(ParkingSpot parkingSpot : floor.getParkingSpots()) {
                if(parkingSpot.getVehicleType().equals(vehicle.getVehicleType()) &&
                parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.FREE)) {
                    return parkingSpot;
                }
            }
        }
        throw new NoParkingSpotAvailableException("No parking spot available for " + vehicle.getVehicleType());
    }
}