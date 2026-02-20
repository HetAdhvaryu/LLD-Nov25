package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.User;
import java.util.List;

public interface UserService {
    User save(User user);
    User getById(int userId);
    void deleteById(int userId);
    List<User> getAll();
    User update(int userId, User newUser);
    User getUserById(int id);
    User getUserByEmail(String email, String password);
}
