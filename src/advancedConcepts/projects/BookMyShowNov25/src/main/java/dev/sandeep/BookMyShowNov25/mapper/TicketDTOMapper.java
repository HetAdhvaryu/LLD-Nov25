package dev.sandeep.BookMyShowNov25.mapper;

import dev.sandeep.BookMyShowNov25.dto.TicketRespDTO;
import dev.sandeep.BookMyShowNov25.entity.ShowSeat;
import dev.sandeep.BookMyShowNov25.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketDTOMapper {

    public  static TicketRespDTO getTicketRespDTO(Ticket ticket) {
        TicketRespDTO ticketRespDTO = new TicketRespDTO();
        ticketRespDTO.setId(ticket.getId());
        ticketRespDTO.setMovieTitle(ticket.getShow().getMovie().getTitle());
        ticketRespDTO.setAuditoriumName(ticket.getShow().getAuditorium().getAuditoriumName());
        ticketRespDTO.setShowStartTime(ticket.getShow().getStartTime());
        List<String> seats = new ArrayList<>();
        for(ShowSeat showSeat : ticket.getShowSeats()){
            seats.add(showSeat.getSeat().getSeatNo());
        }
        ticketRespDTO.setSeatNumbers(seats);
        return ticketRespDTO;
    }
}
