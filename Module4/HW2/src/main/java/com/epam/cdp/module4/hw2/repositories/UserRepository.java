package com.epam.cdp.module4.hw2.repositories;

import com.epam.cdp.module4.hw2.model.User;

import java.util.List;

public interface UserRepository extends IBaseRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByName(String name, int pageSize, int pageNum);
}
