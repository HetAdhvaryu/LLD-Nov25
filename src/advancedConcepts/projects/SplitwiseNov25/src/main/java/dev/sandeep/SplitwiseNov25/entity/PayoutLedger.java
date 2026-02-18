package dev.sandeep.SplitwiseNov25.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class PayoutLedger extends BaseModel {
    @ManyToOne
    private User user;
    private double amount;
    @Enumerated(EnumType.STRING)
    private PayoutType payoutType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PayoutType getPayoutType() {
        return payoutType;
    }

    public void setPayoutType(PayoutType payoutType) {
        this.payoutType = payoutType;
    }
}
