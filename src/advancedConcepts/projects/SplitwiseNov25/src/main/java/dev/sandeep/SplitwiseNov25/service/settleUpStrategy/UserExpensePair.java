package dev.sandeep.SplitwiseNov25.service.settleUpStrategy;

import dev.sandeep.SplitwiseNov25.entity.User;

public class UserExpensePair {
    private User user;
    private Double outstandingAmount;

    public UserExpensePair(User user, Double amount) {
        this.user = user;
        this.outstandingAmount = amount;
    }

    public UserExpensePair() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getAmount() {
        return outstandingAmount;
    }

    public void setAmount(Double amount) {
        this.outstandingAmount = amount;
    }
}

