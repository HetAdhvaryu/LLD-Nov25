package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.Auditorium;
import dev.sandeep.BookMyShowNov25.entity.Theatre;
import dev.sandeep.BookMyShowNov25.repository.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private TheatreRepo theatreRepo;

    @Override
    public List<Auditorium> getAuditoriumsByTheatreId(int theatreId) {
        Theatre theatre = theatreRepo.findById(theatreId).orElse(null);
        if (theatre != null) {
            return theatre.getAuditoriums();
        } else {
            return null;
        }
    }

    @Override
    public Theatre save(Theatre theatre) {
        return theatreRepo.save(theatre);
    }

    @Override
    public Theatre getById(int theatreId) {
        return theatreRepo.findById(theatreId).orElse(null);
    }

    @Override
    public void deleteById(int theatreId) {
        theatreRepo.deleteById(theatreId);
    }

    @Override
    public List<Theatre> getAll() {
        return theatreRepo.findAll();
    }

    @Override
    public Theatre update(int theatreId, Theatre newTheatre) {
        if (theatreRepo.existsById(theatreId)) {
            newTheatre.setId(theatreId);
            return theatreRepo.save(newTheatre);
        }
        return null;
    }
}
