package com.epam.cdp.hw1.repositories;

public interface IBaseRepository<T, ID> {

    T save(T entity);

    T findById(ID id);

    void update(T entity);

    void delete(ID id);

}
