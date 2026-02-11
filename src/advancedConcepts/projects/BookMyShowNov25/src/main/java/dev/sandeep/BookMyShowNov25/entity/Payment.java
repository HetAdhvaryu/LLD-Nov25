package dev.sandeep.BookMyShowNov25.entity;

import dev.sandeep.BookMyShowNov25.entity.constants.TransactionPaymentStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Payment extends BaseModel {
    @OneToMany
    @JoinColumn(name = "payment_id")
    private List<Transaction> transactions; //multi-part payment
    private int totalAmount;
    @Enumerated(EnumType.ORDINAL)
    private TransactionPaymentStatus transactionPaymentStatus;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public TransactionPaymentStatus getPaymentStatus() {
        return transactionPaymentStatus;
    }

    public void setPaymentStatus(TransactionPaymentStatus transactionPaymentStatus) {
        this.transactionPaymentStatus = transactionPaymentStatus;
    }
}
