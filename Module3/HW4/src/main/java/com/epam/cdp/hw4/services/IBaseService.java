package com.epam.cdp.hw4.services;

public interface IBaseService<T> {

    T findById(long id);

    void save(T entity);

    void update(long id);

    void delete(long id);

}
