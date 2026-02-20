package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.ShowSeat;
import java.util.List;

public interface ShowSeatService {
    ShowSeat save(ShowSeat showSeat);
    ShowSeat getById(int showSeatId);
    void deleteById(int showSeatId);
    List<ShowSeat> getAll();
    ShowSeat update(int showSeatId, ShowSeat newShowSeat);
    ShowSeat updateShowSeat(ShowSeat showSeat);
    List<ShowSeat> getShowSeatsByIds(List<Integer> showSeatIds);
}
