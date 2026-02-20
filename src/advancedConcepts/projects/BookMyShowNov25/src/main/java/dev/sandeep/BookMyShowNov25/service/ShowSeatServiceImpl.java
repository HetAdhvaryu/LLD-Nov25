package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.ShowSeat;
import dev.sandeep.BookMyShowNov25.repository.ShowSeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSeatServiceImpl implements ShowSeatService {

    @Autowired
    private ShowSeatRepo showSeatRepo;

    @Override
    public ShowSeat save(ShowSeat showSeat) {
        return showSeatRepo.save(showSeat);
    }

    @Override
    public ShowSeat getById(int showSeatId) {
        return showSeatRepo.findById(showSeatId).orElse(null);
    }

    @Override
    public void deleteById(int showSeatId) {
        showSeatRepo.deleteById(showSeatId);
    }

    @Override
    public List<ShowSeat> getAll() {
        return showSeatRepo.findAll();
    }

    @Override
    public ShowSeat update(int showSeatId, ShowSeat newShowSeat) {
        if (showSeatRepo.existsById(showSeatId)) {
            newShowSeat.setId(showSeatId);
            return showSeatRepo.save(newShowSeat);
        }
        return null;
    }

    @Override
    public ShowSeat updateShowSeat(ShowSeat showSeat){
        return showSeatRepo.save(showSeat); // save is an upsert -> update and insert
    }

    @Override
    public List<ShowSeat> getShowSeatsByIds(List<Integer> showSeatIds) {
        return showSeatRepo.findAllById(showSeatIds);
    }
}
