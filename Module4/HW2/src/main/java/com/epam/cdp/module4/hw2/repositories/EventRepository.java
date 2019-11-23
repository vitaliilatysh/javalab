package com.epam.cdp.module4.hw2.repositories;

import com.epam.cdp.module4.hw2.model.Event;

import java.util.Date;
import java.util.List;

public interface EventRepository extends IBaseRepository<Event, Long> {
    List<Event> findByTitle(String title, int pageSize, int pageNum);

    List<Event> findByDay(Date day, int pageSize, int pageNum);
}
