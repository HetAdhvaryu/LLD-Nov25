package dev.sandeep.SplitwiseNov25.service.settleUpStrategy;

public class SettlupStrategyFactory {

    public static SettleUpStrategy getSettlupStrategy() {
        return new MaxLendorBorrowerMatchSettleUpStrategy();
    }
}
