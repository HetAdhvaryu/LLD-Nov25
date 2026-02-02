package advancedConcepts.projects.parkingLot.service.billingStrategy;

import advancedConcepts.projects.parkingLot.model.Bill;
import advancedConcepts.projects.parkingLot.model.ParkingLot;
import advancedConcepts.projects.parkingLot.model.Ticket;

public interface BillCalculationStrategy {
    Bill generateBill(Ticket ticket, double occupancyRatio);
}
