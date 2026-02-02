package advancedConcepts.projects.parkingLot.repository;

import advancedConcepts.projects.parkingLot.exception.ParkingSpotNotFoundException;
import advancedConcepts.projects.parkingLot.model.ParkingLot;
import advancedConcepts.projects.parkingLot.model.ParkingSpot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingLotRepository {
    //IN-MEMORY DATABASE
    private HashMap<Integer, ParkingLot> parkingLots;
    private static int idCounter = 1;

    public ParkingLotRepository() {
        this.parkingLots = new HashMap<>();
    }

    public ParkingLot getParkingLotById(int id) {
        if(parkingLots.containsKey(id)) {
            return parkingLots.get(id);
        } else {
          throw new ParkingSpotNotFoundException("ParkingSpot with id " + id + " not found");
        }
    }

    public ParkingLot save(ParkingLot parkingLot) { // upsert method -> save and update
        if(!parkingLots.containsKey(parkingLot.getId())) {
            parkingLot.setId(idCounter++);
        }
        parkingLots.put(parkingLot.getId(), parkingLot);
        return parkingLot;
    }

    public void delete(int parkingLotId) {
        parkingLots.remove(parkingLotId);
    }

    public List<ParkingLot> getAllParkingSpots() {
        return new ArrayList<>(parkingLots.values());
    }
}
