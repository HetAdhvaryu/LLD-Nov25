package advancedConcepts.projects.parkingLot.repository;

import advancedConcepts.projects.parkingLot.exception.ParkingFloorNotFoundException;
import advancedConcepts.projects.parkingLot.model.ParkingFloor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingFloorRepository {
    //IN-MEMORY DATABASE
    private HashMap<Integer, ParkingFloor> parkingFloors;
    private static int idCounter = 1;

    public ParkingFloorRepository() {
        this.parkingFloors = new HashMap<>();
    }

    public ParkingFloor getParkingFloorById(int id) {
        if(parkingFloors.containsKey(id)) {
            return parkingFloors.get(id);
        } else {
          throw new ParkingFloorNotFoundException("ParkingFloor with id " + id + " not found");
        }
    }

    public ParkingFloor save(ParkingFloor parkingFloor) { // upsert method -> save and update
        if(!parkingFloors.containsKey(parkingFloor.getId())) {
            parkingFloor.setId(idCounter++);
        }
        parkingFloors.put(parkingFloor.getId(), parkingFloor);
        return parkingFloor;
    }
    
    public void delete(int parkingFloorId) {
        parkingFloors.remove(parkingFloorId);
    }
    
    public List<ParkingFloor> getAllParkingFloors() {
        return new ArrayList<>(parkingFloors.values());
    }
}
