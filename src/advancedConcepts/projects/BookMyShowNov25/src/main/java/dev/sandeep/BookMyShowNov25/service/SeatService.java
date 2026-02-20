package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.Seat;
import java.util.List;

public interface SeatService {
    Seat save(Seat seat);
    Seat getById(int seatId);
    void deleteById(int seatId);
    List<Seat> getAll();
    Seat update(int seatId, Seat newSeat);
}
