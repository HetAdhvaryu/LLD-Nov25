package dev.sandeep.SplitwiseNov25.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity(name = "SW_Transaction")
public class Transaction extends BaseModel{
    private double amount;
    @ManyToOne
    private User paidBy;
    @ManyToOne
    private User receivedBy;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public User getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(User receivedBy) {
        this.receivedBy = receivedBy;
    }
}
