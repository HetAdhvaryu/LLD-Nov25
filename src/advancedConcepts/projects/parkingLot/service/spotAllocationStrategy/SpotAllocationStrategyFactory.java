package advancedConcepts.projects.parkingLot.service.spotAllocationStrategy;

public class SpotAllocationStrategyFactory {

    public static SpotAllocationStrategy getSpotAllocationStrategy() {
        //TODO: Complete the strategy design pattern based factory method here
        return new LinearSpotAllocationStrategy();
    }
}
