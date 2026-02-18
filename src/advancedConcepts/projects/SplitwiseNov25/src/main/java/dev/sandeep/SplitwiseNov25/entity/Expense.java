package dev.sandeep.SplitwiseNov25.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Expense extends BaseModel{
    private double amount;
    private String description;
    @OneToMany
    @JoinColumn(name = "expense_id")
    private List<PayoutLedger> payoutLedgers;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PayoutLedger> getPayoutLedgers() {
        return payoutLedgers;
    }

    public void setPayoutLedgers(List<PayoutLedger> payoutLedgers) {
        this.payoutLedgers = payoutLedgers;
    }
}
