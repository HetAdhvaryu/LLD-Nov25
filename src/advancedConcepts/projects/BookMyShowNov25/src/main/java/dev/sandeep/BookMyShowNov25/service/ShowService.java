package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.Show;
import java.util.List;

public interface ShowService {
    Show save(Show show);
    Show getById(int showId);
    void deleteById(int showId);
    List<Show> getAll();
    Show update(int showId, Show newShow);
    Show getShowById(int id);
}
