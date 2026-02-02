package advancedConcepts.projects.parkingLot.repository;

import advancedConcepts.projects.parkingLot.exception.ParkingSpotNotFoundException;
import advancedConcepts.projects.parkingLot.model.ParkingSpot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingSpotRepository {
    //IN-MEMORY DATABASE
    private HashMap<Integer, ParkingSpot> parkingSpots;
    private static int idCounter = 1;

    public ParkingSpotRepository() {
        this.parkingSpots = new HashMap<>();
    }

    public ParkingSpot getParkingSpotById(int id) {
        if(parkingSpots.containsKey(id)) {
            return parkingSpots.get(id);
        } else {
          throw new ParkingSpotNotFoundException("ParkingSpot with id " + id + " not found");
        }
    }

    public ParkingSpot save(ParkingSpot parkingSpot) { // upsert method -> save and update
        if(!parkingSpots.containsKey(parkingSpot.getId())) {
            parkingSpot.setId(idCounter++);
        }
        parkingSpots.put(parkingSpot.getId(), parkingSpot);
        return parkingSpot;
    }

    public void delete(int parkingSpotId) {
        parkingSpots.remove(parkingSpotId);
    }

    public List<ParkingSpot> getAllParkingSpots() {
        return new ArrayList<>(parkingSpots.values());
    }
}
