package dev.sandeep.BookMyShowNov25.repository;

import dev.sandeep.BookMyShowNov25.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepo extends JpaRepository<ShowSeat, Integer> {
}
