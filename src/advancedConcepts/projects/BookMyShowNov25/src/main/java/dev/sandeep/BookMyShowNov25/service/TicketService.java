package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.Ticket;
import java.util.List;

public interface TicketService {
    Ticket save(Ticket ticket);
    Ticket getById(int ticketId);
    void deleteById(int ticketId);
    List<Ticket> getAll();
    Ticket update(int ticketId, Ticket newTicket);
    Ticket createTicket(List<Integer> showSeatIds, Integer userId);
}
