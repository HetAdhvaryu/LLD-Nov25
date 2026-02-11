package dev.sandeep.BookMyShowNov25.repository;

import dev.sandeep.BookMyShowNov25.entity.City;
import dev.sandeep.BookMyShowNov25.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepo extends JpaRepository<Theatre, Integer> {
}
