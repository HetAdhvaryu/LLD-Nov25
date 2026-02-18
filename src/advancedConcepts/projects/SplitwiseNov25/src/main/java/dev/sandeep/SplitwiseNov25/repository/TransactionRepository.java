package dev.sandeep.SplitwiseNov25.repository;

import dev.sandeep.SplitwiseNov25.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
