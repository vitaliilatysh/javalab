package com.epam.cdp.hw1.repositories.impl;

import com.epam.cdp.hw1.model.Event;
import com.epam.cdp.hw1.repositories.EventRepository;
import com.epam.cdp.hw1.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;

public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private Storage storage;

    @Override
    public Event save(Event entity) {
        storage.getEventStorage().put("3", entity);
        return storage.getEventStorage().get("3");
    }

    @Override
    public Event findById(Long id) {
        return storage.getEventStorage().get(Long.toString(id));
    }

    @Override
    public void update(Event entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
