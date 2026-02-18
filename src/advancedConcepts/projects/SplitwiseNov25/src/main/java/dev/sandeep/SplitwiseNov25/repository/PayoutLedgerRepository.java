package dev.sandeep.SplitwiseNov25.repository;

import dev.sandeep.SplitwiseNov25.entity.PayoutLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayoutLedgerRepository extends JpaRepository<PayoutLedger, Integer> {
}
