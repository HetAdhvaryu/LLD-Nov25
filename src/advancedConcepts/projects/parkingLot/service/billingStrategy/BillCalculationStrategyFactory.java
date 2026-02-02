package advancedConcepts.projects.parkingLot.service.billingStrategy;

public class BillCalculationStrategyFactory {
    // TODO : add enum selection based object creation for the different type of subscription implementations

    public static BillCalculationStrategy getBillCalculationStrategy(BillCalculationStrategies billCalculationStrategies) {
        return new SimpleBillCalculationStrategy();
    }
}
