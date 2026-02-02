package advancedConcepts.projects.parkingLot.service.billingStrategy;

import advancedConcepts.projects.parkingLot.model.Ticket;

import java.time.LocalDateTime;

public class SimpleBillCalculationStrategy implements BillCalculationStrategy{

    @Override
    public double calculateBillCost(Ticket ticket, double occupancyRatio) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();
        long numberOfSeconds = java.time.temporal.ChronoUnit.SECONDS.between(entryTime, exitTime);
        //TODO: find number of hours -- 68 mins > 1 hour 8 mins -> 1 initial hour + 1 additional hour
        int numberOfHours = 5; // hard-coded for now
        int initialCost = CostConfiguration.initialHourCostMap.get(ticket.getVehicle().getVehicleType());
        int additionalCost = CostConfiguration.additionalHourCostMap.get(ticket.getVehicle().getVehicleType()) * (numberOfHours - 1);
        return initialCost + additionalCost;
    }
}
