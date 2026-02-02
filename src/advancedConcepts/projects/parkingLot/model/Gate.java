package advancedConcepts.projects.parkingLot.model;

import advancedConcepts.projects.parkingLot.model.constants.GateStatus;
import advancedConcepts.projects.parkingLot.model.constants.GateType;

public class Gate extends BaseModel{
    private GateType gateType;
    private GateStatus gateStatus;
    private Operator operator;

    public Gate() {
    }

    public Gate(GateType gateType, GateStatus gateStatus, Operator operator) {
        this.gateType = gateType;
        this.gateStatus = gateStatus;
        this.operator = operator;
    }

    public GateType getGateType() {
        return gateType;
    }

    public void setGateType(GateType gateType) {
        this.gateType = gateType;
    }

    public GateStatus getGateStatus() {
        return gateStatus;
    }

    public void setGateStatus(GateStatus gateStatus) {
        this.gateStatus = gateStatus;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
