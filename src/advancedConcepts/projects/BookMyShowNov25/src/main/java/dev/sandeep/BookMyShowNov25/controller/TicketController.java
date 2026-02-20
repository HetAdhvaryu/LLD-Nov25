package dev.sandeep.BookMyShowNov25.controller;

import dev.sandeep.BookMyShowNov25.dto.TicketReqDTO;
import dev.sandeep.BookMyShowNov25.dto.TicketRespDTO;
import dev.sandeep.BookMyShowNov25.entity.Ticket;
import dev.sandeep.BookMyShowNov25.mapper.TicketDTOMapper;
import dev.sandeep.BookMyShowNov25.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * API to book a ticket
     * POST /ticket/book
     *
     * Request Body:
     * {
     *   "userId": 1,
     *   "showSeatIds": [1, 2, 3]
     * }
     *
     * Response:
     * {
     *   "id": 1,
     *   "movieTitle": "Pathaan",
     *   "showStartTime": "2026-02-21T10:00:00",
     *   "seatNumbers": ["11", "12", "13"],
     *   "auditoriumName": "Audi 01",
     *   "theatreName": "PVR Cinemas"
     * }
     */
    @PostMapping("/book")
    public ResponseEntity<TicketRespDTO> bookTicket(@RequestBody TicketReqDTO ticketReqDTO) {
        try {
            // Extract userId and showSeatIds from request DTO
            int userId = ticketReqDTO.getUserId();
            java.util.List<Integer> showSeatIds = ticketReqDTO.getShowSeatIds();

            // Call service to create ticket
            Ticket ticket = ticketService.createTicket(showSeatIds, userId);

            // Map entity to response DTO
            TicketRespDTO ticketRespDTO = TicketDTOMapper.getTicketRespDTO(ticket);

            // Return success response
            return new ResponseEntity<>(ticketRespDTO, HttpStatus.CREATED);

        } catch (Exception e) {
            // Log the error (in production, use proper logging)
            System.err.println("Error booking ticket: " + e.getMessage());

            // Return error response with empty body
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
