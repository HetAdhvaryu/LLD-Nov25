package dev.sandeep.BookMyShowNov25.entity;

import java.util.List;

public class Payment extends BaseModel {
    private List<Trnsaction> transactions; //multi-part payment
    private int totalAmount;
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
