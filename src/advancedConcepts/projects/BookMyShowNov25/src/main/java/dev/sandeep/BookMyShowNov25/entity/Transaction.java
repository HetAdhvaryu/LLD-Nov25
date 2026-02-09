package dev.sandeep.BookMyShowNov25.entity;

import dev.sandeep.BookMyShowNov25.entity.constants.PaymentType;
import dev.sandeep.BookMyShowNov25.entity.constants.TransactionPaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Transaction extends BaseModel{
    private int transactionAmount;
    @Enumerated(EnumType.ORDINAL)
    private TransactionPaymentStatus transactionPaymentStatus;
    private String referenceNumber;
    @Enumerated(EnumType.ORDINAL)
    private PaymentType paymentType;

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public TransactionPaymentStatus getPaymentStatus() {
        return transactionPaymentStatus;
    }

    public void setPaymentStatus(TransactionPaymentStatus transactionPaymentStatus) {
        this.transactionPaymentStatus = transactionPaymentStatus;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
