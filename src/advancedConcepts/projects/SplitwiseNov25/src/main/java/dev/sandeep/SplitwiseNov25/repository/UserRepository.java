package dev.sandeep.SplitwiseNov25.repository;

import dev.sandeep.SplitwiseNov25.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
