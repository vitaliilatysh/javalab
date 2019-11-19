package com.epam.cdp.hw1.repositories;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public interface IBaseRepository<T, ID> {

    T save(T entity);

    T findById(ID id);

    T update(T entity);

    boolean delete(ID id);

    /**
     * Return elements as pages
     *
     * @param pageSize        page size
     * @param pageNum         page num
     * @param storageElements storage elements
     * @return list
     */
    default List<T> getItems(int pageSize, int pageNum, List<T> storageElements) {
        List<List<T>> partitions = new LinkedList<>();
        for (int index = 0; index < storageElements.size(); index += pageSize) {
            partitions.add(storageElements.subList(index,
                    Math.min(index + pageSize, storageElements.size())));
        }

        if (partitions.isEmpty() || pageNum >= partitions.size()) {
            return Collections.emptyList();
        }
        return partitions.get(pageNum);
    }
}
