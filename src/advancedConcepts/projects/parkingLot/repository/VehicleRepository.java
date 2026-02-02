package advancedConcepts.projects.parkingLot.repository;

import advancedConcepts.projects.parkingLot.model.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VehicleRepository {
    // IN-MEMORY DATABASE
    private HashMap<Integer, Vehicle> vehicles;
    private static int idCounter = 1;

    public VehicleRepository() {
        this.vehicles = new HashMap<>();
    }

    public Vehicle getVehicleById(int id) {
        if (vehicles.containsKey(id)) {
            return vehicles.get(id);
        } else {
            throw new RuntimeException("Vehicle with id " + id + " not found");
        }
    }

    public Vehicle save(Vehicle vehicle) { // upsert
        if (!vehicles.containsKey(vehicle.getId())) {
            vehicle.setId(idCounter++);
        }
        vehicles.put(vehicle.getId(), vehicle);
        return vehicle;
    }

    public void delete(int vehicleId) {
        vehicles.remove(vehicleId);
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicles.values());
    }
}
