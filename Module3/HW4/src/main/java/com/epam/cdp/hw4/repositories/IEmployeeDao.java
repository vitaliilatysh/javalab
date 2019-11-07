package com.epam.cdp.hw4.repositories;

import java.util.List;

public interface IEmployeeDao<T> {

    void save(T entity);

    void update(T entity);

    T findById(long id);

    void delete(T entity);

    List<T> findAll();

}
