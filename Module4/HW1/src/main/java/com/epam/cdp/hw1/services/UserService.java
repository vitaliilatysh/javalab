package com.epam.cdp.hw1.services;

import com.epam.cdp.hw1.model.User;
import com.epam.cdp.hw1.repositories.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    private UserRepositoryImpl userRepository;

    public User findById(long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.update(user);
    }

    public boolean delete(long userId) {
        return userRepository.delete(userId);
    }

    public List<User> getByName(String name, int pageSize, int pageNum) {
        return userRepository.findByName(name, pageSize, pageNum);
    }
}
