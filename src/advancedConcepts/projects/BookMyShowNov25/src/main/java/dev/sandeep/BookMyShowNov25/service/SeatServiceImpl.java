package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.Seat;
import dev.sandeep.BookMyShowNov25.repository.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepo seatRepo;

    @Override
    public Seat save(Seat seat) {
        return seatRepo.save(seat);
    }

    @Override
    public Seat getById(int seatId) {
        return seatRepo.findById(seatId).orElse(null);
    }

    @Override
    public void deleteById(int seatId) {
        seatRepo.deleteById(seatId);
    }

    @Override
    public List<Seat> getAll() {
        return seatRepo.findAll();
    }

    @Override
    public Seat update(int seatId, Seat newSeat) {
        if (seatRepo.existsById(seatId)) {
            newSeat.setId(seatId);
            return seatRepo.save(newSeat);
        }
        return null;
    }
}
