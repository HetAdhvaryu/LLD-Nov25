package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.ShowSeat;
import dev.sandeep.BookMyShowNov25.repository.ShowSeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSeatServiceImpl {

    @Autowired
    private ShowSeatRepo showSeatRepo;

    public ShowSeat updateShowSeat(ShowSeat showSeat){
        return showSeatRepo.save(showSeat); // save is an upsert -> update and insert
    }

    public List<ShowSeat> getShowSeatsByIds(List<Integer> showSeatIds) {
        return showSeatRepo.findAllById(showSeatIds);
    }
}
