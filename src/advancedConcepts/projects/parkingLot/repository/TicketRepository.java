package advancedConcepts.projects.parkingLot.repository;

import advancedConcepts.projects.parkingLot.model.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TicketRepository {
    // IN-MEMORY DATABASE
    private HashMap<Integer, Ticket> tickets;
    private static int idCounter = 1;

    public TicketRepository() {
        this.tickets = new HashMap<>();
    }

    public Ticket getTicketById(int id) {
        if (tickets.containsKey(id)) {
            return tickets.get(id);
        } else {
            throw new RuntimeException("Ticket with id " + id + " not found");
        }
    }

    public Ticket save(Ticket ticket) { // upsert
        if (!tickets.containsKey(ticket.getId())) {
            ticket.setId(idCounter++);
        }
        tickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public void delete(int ticketId) {
        tickets.remove(ticketId);
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets.values());
    }
}
