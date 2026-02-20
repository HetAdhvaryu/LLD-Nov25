package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.Payment;
import dev.sandeep.BookMyShowNov25.entity.ShowSeat;
import dev.sandeep.BookMyShowNov25.entity.Ticket;
import dev.sandeep.BookMyShowNov25.entity.User;
import dev.sandeep.BookMyShowNov25.entity.constants.ShowSeatStatus;
import dev.sandeep.BookMyShowNov25.entity.constants.TicketStatus;
import dev.sandeep.BookMyShowNov25.exception.InvalidShowSeatSelectionException;
import dev.sandeep.BookMyShowNov25.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ShowSeatServiceImpl showSeatServiceImpl;
    @Autowired
    private ShowServiceImpl showServiceImpl;

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    @Override
    public Ticket getById(int ticketId) {
        return ticketRepo.findById(ticketId).orElse(null);
    }

    @Override
    public void deleteById(int ticketId) {
        ticketRepo.deleteById(ticketId);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepo.findAll();
    }

    @Override
    public Ticket update(int ticketId, Ticket newTicket) {
        if (ticketRepo.existsById(ticketId)) {
            newTicket.setId(ticketId);
            return ticketRepo.save(newTicket);
        }
        return null;
    }


    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Ticket createTicket(List<Integer> showSeatIds, Integer userId){
        User user = userServiceImpl.getUserById(userId);
        List<ShowSeat> showSeats = showSeatServiceImpl.getShowSeatsByIds(showSeatIds);

        //CHECK IF SEATS ARE AVAILABLE
        for(ShowSeat showSeat : showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new InvalidShowSeatSelectionException("ShowSeat selected is not available, showSeatId: " + showSeat.getId());
            }
        }
        //LOCK THE SEATS -- blocking the seats until the payment is not done
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeatServiceImpl.updateShowSeat(showSeat);
        }
        // NOTE -> Cant merge the above two loops as we need to make sure every seat is available before locking anything

        //TODO: PAYMENT PROCESS DONE HERE
        Payment payment = new Payment();

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShowSeats(showSeats);
        ticket.setPayment(payment);
        ticket.setShow(showServiceImpl.getShowById(showSeats.getFirst().getShow().getId()));
        ticket.setTicketStatus(TicketStatus.BOOKED);

        Ticket savedTicket = ticketRepo.save(ticket);

        //BOOK THE SEATS
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
            showSeatServiceImpl.updateShowSeat(showSeat);
        }
        return savedTicket;
    }

}
