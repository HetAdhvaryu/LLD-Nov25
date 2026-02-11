package dev.sandeep.BookMyShowNov25.repository;

import dev.sandeep.BookMyShowNov25.entity.Payment;
import dev.sandeep.BookMyShowNov25.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {
}
