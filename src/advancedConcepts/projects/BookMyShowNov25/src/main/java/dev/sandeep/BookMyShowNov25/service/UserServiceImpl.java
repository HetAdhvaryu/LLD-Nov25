package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.User;
import dev.sandeep.BookMyShowNov25.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getById(int userId) {
        return userRepo.findById(userId).orElse(null);
    }

    @Override
    public void deleteById(int userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User update(int userId, User newUser) {
        if (userRepo.existsById(userId)) {
            newUser.setId(userId);
            return userRepo.save(newUser);
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getUserById(int id) {
        return userRepo.findById(id).orElse(null);
    }
}
