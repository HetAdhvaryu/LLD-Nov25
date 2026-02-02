package advancedConcepts.projects.parkingLot.repository;

import advancedConcepts.projects.parkingLot.exception.GateNotFoundException;
import advancedConcepts.projects.parkingLot.model.Gate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GateRepository {
    //IN-MEMORY DATABASE
    private HashMap<Integer, Gate> gates;
    private static int idCounter = 1;

    public GateRepository() {
        this.gates = new HashMap<>();
    }

    public Gate getGateById(int id) {
        if(gates.containsKey(id)) {
            return gates.get(id);
        } else {
          throw new GateNotFoundException("Gate with id " + id + " not found");
        }
    }

    public Gate save(Gate gate) { // upsert method -> save and update
        if(!gates.containsKey(gate.getId())) {
            gate.setId(idCounter++);
        }
        gates.put(gate.getId(), gate);
        return gate;
    }

    public void delete(int gateId) {
        gates.remove(gateId);
    }

    public List<Gate> getAllGates() {
        return new ArrayList<>(gates.values());
    }
}
