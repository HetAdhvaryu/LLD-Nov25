package dev.sandeep.BookMyShowNov25.dto;

import dev.sandeep.BookMyShowNov25.entity.Ticket;

import java.util.List;

public class CreateUserRespDTO {
    private int id;
    private String name;
    private String email;
    private List<TicketRespDTO> tickets;

    public CreateUserRespDTO() {
    }

    public CreateUserRespDTO(int id, String name, String email, List<TicketRespDTO> tickets) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TicketRespDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketRespDTO> tickets) {
        this.tickets = tickets;
    }
}
