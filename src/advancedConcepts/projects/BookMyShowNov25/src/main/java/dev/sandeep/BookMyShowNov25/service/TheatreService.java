package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.Auditorium;
import dev.sandeep.BookMyShowNov25.entity.Theatre;
import java.util.List;

public interface TheatreService {
    List<Auditorium> getAuditoriumsByTheatreId(int theatreId);
    Theatre save(Theatre theatre);
    Theatre getById(int theatreId);
    void deleteById(int theatreId);
    List<Theatre> getAll();
    Theatre update(int theatreId, Theatre newTheatre);
}
