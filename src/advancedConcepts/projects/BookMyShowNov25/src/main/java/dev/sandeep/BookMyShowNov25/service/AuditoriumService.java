package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.Auditorium;
import java.util.List;

public interface AuditoriumService {
    Auditorium save(Auditorium auditorium);
    Auditorium getById(int auditoriumId);
    void deleteById(int auditoriumId);
    List<Auditorium> getAll();
    Auditorium update(int auditoriumId, Auditorium newAuditorium);
}
