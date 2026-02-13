package dev.sandeep.BookMyShowNov25.mapper;

import dev.sandeep.BookMyShowNov25.dto.CreateUserReqDTO;
import dev.sandeep.BookMyShowNov25.dto.CreateUserRespDTO;
import dev.sandeep.BookMyShowNov25.dto.TicketRespDTO;
import dev.sandeep.BookMyShowNov25.entity.Ticket;
import dev.sandeep.BookMyShowNov25.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTOMapper {

    public static User getUser(CreateUserReqDTO createUserReqDTO) {
        User user = new User();
        user.setName(createUserReqDTO.getName());
        user.setEmail(createUserReqDTO.getEmail());
        user.setPassword(createUserReqDTO.getPassword());
        return user;
    }

    public static CreateUserRespDTO getCreateUserRespDTO(User user) {
        CreateUserRespDTO createUserRespDTO = new CreateUserRespDTO();
        createUserRespDTO.setId(user.getId());
        createUserRespDTO.setName(user.getName());
        createUserRespDTO.setEmail(user.getEmail());
        if(user.getTickets() != null) {
            List<TicketRespDTO> tickets = new ArrayList<>();
                for (Ticket ticket : user.getTickets()) {
                    tickets.add(TicketDTOMapper.getTicketRespDTO(ticket));
                }
            createUserRespDTO.setTickets(tickets);
        }
        return createUserRespDTO;
    }
}
