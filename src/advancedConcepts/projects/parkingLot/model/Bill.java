package advancedConcepts.projects.parkingLot.model;

import advancedConcepts.projects.parkingLot.model.constants.BillStatus;
import advancedConcepts.projects.parkingLot.model.constants.PaymentMode;

import java.time.LocalDateTime;

public class Bill extends BaseModel{
    private LocalDateTime exitTime;
    private double amount;
    private BillStatus billStatus;
    private Ticket ticket;
    private PaymentMode paymentMode;
    private String paymentRefNumber;

    public Bill() {
    }

    public Bill(LocalDateTime exitTime, double amount, BillStatus billStatus, Ticket ticket, PaymentMode paymentMode, String paymentRefNumber) {
        this.exitTime = exitTime;
        this.amount = amount;
        this.billStatus = billStatus;
        this.ticket = ticket;
        this.paymentMode = paymentMode;
        this.paymentRefNumber = paymentRefNumber;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentRefNumber() {
        return paymentRefNumber;
    }

    public void setPaymentRefNumber(String paymentRefNumber) {
        this.paymentRefNumber = paymentRefNumber;
    }
}
