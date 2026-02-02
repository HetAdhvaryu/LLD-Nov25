package advancedConcepts.projects.parkingLot.service.billingStrategy;

import advancedConcepts.projects.parkingLot.model.constants.VehicleType;

import java.util.HashMap;

public class CostConfiguration {

    public static HashMap<VehicleType, Integer> initialHourCostMap;
    public static HashMap<VehicleType, Integer> additionalHourCostMap;
    public static final double SURGE_MULTIPLIER = 1.5;

    /*
          5 hours - TWO WHEELER
          1st hour - 10
          2nd to 5th hour - next 4 hours - 5 * 4 = 20
          total = 30

          if surge applicable

          totalCost * surge

     */

    static {
        initialHourCostMap = new HashMap<>();
        initialHourCostMap.put(VehicleType.TWO_WHEELER, 10);
        initialHourCostMap.put(VehicleType.FOUR_WHEELER, 20);

        additionalHourCostMap = new HashMap<>();
        additionalHourCostMap.put(VehicleType.TWO_WHEELER, 5);
        additionalHourCostMap.put(VehicleType.FOUR_WHEELER, 10);
    }
}
