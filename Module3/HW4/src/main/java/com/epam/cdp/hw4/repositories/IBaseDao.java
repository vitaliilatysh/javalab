package com.epam.cdp.hw4.repositories;

public interface IBaseDao<T> {

    void save(T entity);

    void update(long id);

    T findById(long id);

    void delete(long id);

}
