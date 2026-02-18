package dev.sandeep.SplitwiseNov25.service.settleUpStrategy;

import dev.sandeep.SplitwiseNov25.entity.Expense;
import dev.sandeep.SplitwiseNov25.entity.Transaction;

import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> settleUp(List<Expense> expenses);
}
