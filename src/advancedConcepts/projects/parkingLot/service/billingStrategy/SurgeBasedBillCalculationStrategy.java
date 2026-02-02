package advancedConcepts.projects.parkingLot.service.billingStrategy;

import advancedConcepts.projects.parkingLot.model.Bill;
import advancedConcepts.projects.parkingLot.model.Ticket;
import advancedConcepts.projects.parkingLot.model.constants.BillStatus;
import advancedConcepts.projects.parkingLot.model.constants.PaymentMode;

import java.time.LocalDateTime;

public class SurgeBasedBillCalculationStrategy implements BillCalculationStrategy{
    @Override
    public Bill generateBill(Ticket ticket, double occupancyRatio) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();
        long numberOfSeconds = java.time.temporal.ChronoUnit.SECONDS.between(entryTime, exitTime);
        //TODO: find number of hours -- 68 mins > 1 hour 8 mins -> 1 initial hour + 1 additional hour
        int numberOfHours = 5; // hard-coded for now
        int initialCost = CostConfiguration.initialHourCostMap.get(ticket.getVehicle().getVehicleType());
        int additionalCost = CostConfiguration.additionalHourCostMap.get(ticket.getVehicle().getVehicleType()) * (numberOfHours - 1);
        double totalCost = initialCost + additionalCost;

        //surge pricing
        if(occupancyRatio >= 0.5 && occupancyRatio < 0.75){
            totalCost = totalCost * CostConfiguration.SURGE_MULTIPLIER;
        } else if(occupancyRatio >= 0.75 && occupancyRatio < 0.9){
            totalCost = totalCost * CostConfiguration.SURGE_MULTIPLIER * 1.5;
        } else if (occupancyRatio >= 0.9 && occupancyRatio < 1.0){
            totalCost = totalCost * CostConfiguration.SURGE_MULTIPLIER * 2;
        }

        Bill bill = new Bill();
        bill.setExitTime(exitTime);
        bill.setAmount(totalCost);
        bill.setTicket(ticket);
        bill.setBillStatus(BillStatus.PAID);
        bill.setPaymentMode(PaymentMode.UPI);
        bill.setPaymentRefNumber("PAY12345");
        return bill;
    }
}
