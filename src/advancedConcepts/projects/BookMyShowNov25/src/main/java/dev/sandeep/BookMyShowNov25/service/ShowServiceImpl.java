package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.Show;
import dev.sandeep.BookMyShowNov25.repository.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepo showRepo;

    @Override
    public Show save(Show show) {
        return showRepo.save(show);
    }

    @Override
    public Show getById(int showId) {
        return showRepo.findById(showId).orElse(null);
    }

    @Override
    public void deleteById(int showId) {
        showRepo.deleteById(showId);
    }

    @Override
    public List<Show> getAll() {
        return showRepo.findAll();
    }

    @Override
    public Show update(int showId, Show newShow) {
        if (showRepo.existsById(showId)) {
            newShow.setId(showId);
            return showRepo.save(newShow);
        }
        return null;
    }

    @Override
    public Show getShowById(int id) {
        return showRepo.findById(id).orElse(null);
    }
}
