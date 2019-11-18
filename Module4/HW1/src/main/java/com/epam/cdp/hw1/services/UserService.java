package com.epam.cdp.hw1.services;

import com.epam.cdp.hw1.repositories.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserRepositoryImpl userRepository;
}
