package dev.sandeep.BookMyShowNov25.entity;

import dev.sandeep.BookMyShowNov25.entity.constants.TicketStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ticket extends BaseModel {
    @OneToOne
    private Payment payment;
    @ManyToOne
    private User user; // TICKET:USER ::  1 ticket -> 1 user , 1 user -> M tickets
    @ManyToOne
    private Show show;
    @OneToMany
    @JoinColumn(name = "ticket_id")
    private List<ShowSeat> showSeats;
    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<ShowSeat> getShowSeats() {
        return showSeats;
    }

    public void setShowSeats(List<ShowSeat> showSeats) {
        this.showSeats = showSeats;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
