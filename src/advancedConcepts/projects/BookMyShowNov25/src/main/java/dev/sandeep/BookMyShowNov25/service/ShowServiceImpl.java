package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.Show;
import dev.sandeep.BookMyShowNov25.repository.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowServiceImpl {

    @Autowired
    private ShowRepo showRepo;


    public Show getShowById(int id) {
        return showRepo.findById(id).get();
    }
}
