package com.epam.cdp.hw1.repositories;

import com.epam.cdp.hw1.model.Event;

import java.util.Date;
import java.util.List;

public interface EventRepository extends IBaseRepository<Event, Long> {
    List<Event> findByTitle(String title, int pageSize, int pageNum);

    List<Event> findByDay(Date day, int pageSize, int pageNum);
}
