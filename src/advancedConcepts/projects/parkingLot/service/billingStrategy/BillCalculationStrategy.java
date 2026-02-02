package advancedConcepts.projects.parkingLot.service.billingStrategy;

import advancedConcepts.projects.parkingLot.model.Ticket;

public interface BillCalculationStrategy {
    double calculateBillCost(Ticket ticket, double occupancyRatio);
}
