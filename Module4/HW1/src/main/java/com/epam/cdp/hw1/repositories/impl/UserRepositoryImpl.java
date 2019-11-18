package com.epam.cdp.hw1.repositories.impl;

import com.epam.cdp.hw1.model.User;
import com.epam.cdp.hw1.repositories.IBaseRepository;
import com.epam.cdp.hw1.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryImpl implements IBaseRepository<User, Long> {

    @Autowired
    private Storage storage;

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public User findById(Long aLong) {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
