package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.Auditorium;
import dev.sandeep.BookMyShowNov25.repository.AuditoriumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private AuditoriumRepo auditoriumRepo;

    @Override
    public Auditorium save(Auditorium auditorium) {
        return auditoriumRepo.save(auditorium);
    }

    @Override
    public Auditorium getById(int auditoriumId) {
        return auditoriumRepo.findById(auditoriumId).orElse(null);
    }

    @Override
    public void deleteById(int auditoriumId) {
        auditoriumRepo.deleteById(auditoriumId);
    }

    @Override
    public List<Auditorium> getAll() {
        return auditoriumRepo.findAll();
    }

    @Override
    public Auditorium update(int auditoriumId, Auditorium newAuditorium) {
        if (auditoriumRepo.existsById(auditoriumId)) {
            newAuditorium.setId(auditoriumId);
            return auditoriumRepo.save(newAuditorium);
        }
        return null;
    }
}
