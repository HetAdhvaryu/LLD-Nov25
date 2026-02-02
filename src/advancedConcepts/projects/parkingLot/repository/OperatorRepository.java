package advancedConcepts.projects.parkingLot.repository;

import advancedConcepts.projects.parkingLot.model.Operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperatorRepository {
    // IN-MEMORY DATABASE
    private HashMap<Integer, Operator> operators;
    private static int idCounter = 1;

    public OperatorRepository() {
        this.operators = new HashMap<>();
    }

    public Operator getOperatorById(int id) {
        if (operators.containsKey(id)) {
            return operators.get(id);
        } else {
            throw new RuntimeException("Operator with id " + id + " not found");
        }
    }

    public Operator save(Operator operator) { // upsert
        if (!operators.containsKey(operator.getId())) {
            operator.setId(idCounter++);
        }
        operators.put(operator.getId(), operator);
        return operator;
    }

    public void delete(int operatorId) {
        operators.remove(operatorId);
    }

    public List<Operator> getAllOperators() {
        return new ArrayList<>(operators.values());
    }
}
