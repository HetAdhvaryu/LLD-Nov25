package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.config.BCryptEncoder;
import dev.sandeep.BookMyShowNov25.entity.User;
import dev.sandeep.BookMyShowNov25.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptEncoder bCryptEncoder;

    @Override
    public User save(User user) {
        user.setPassword(bCryptEncoder.encode(user.getPassword()));
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
    public User getUserById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User getUserByEmail(String email, String password) {
        User savedUser = userRepo.findByEmail(email);
        if(savedUser != null && bCryptEncoder.matches(password, savedUser.getPassword())) {
            return savedUser;
        } else {
            return null;
        }
    }
}
