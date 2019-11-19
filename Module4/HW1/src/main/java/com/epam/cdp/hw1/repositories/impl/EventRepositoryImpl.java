package com.epam.cdp.hw1.repositories.impl;

import com.epam.cdp.hw1.model.Event;
import com.epam.cdp.hw1.repositories.EventRepository;
import com.epam.cdp.hw1.storage.Storage;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventRepositoryImpl implements EventRepository {

    private Storage storage;

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Event save(Event entity) {
        String newEntryId = String.valueOf(entity.getId());
        storage.getEventStorage().put(newEntryId, entity);
        return storage.getEventStorage().get(newEntryId);
    }

    @Override
    public Event findById(Long id) {
        return storage.getEventStorage().get(Long.toString(id));
    }

    @Override
    public Event update(Event entity) {
        String updatedEntityId = String.valueOf(entity.getId());
        return storage.getEventStorage().put(updatedEntityId, entity);
    }

    @Override
    public boolean delete(Long id) {
        Event event = storage.getEventStorage().remove(String.valueOf(id));
        return event != null;
    }

    @Override
    public List<Event> findByTitle(String title, int pageSize, int pageNum) {
        List<Event> storageEvents = storage.getEventStorage().values().stream()
                .filter(event -> event.getTitle().contains(title))
                .collect(Collectors.toList());

        return getItems(pageSize, pageNum, storageEvents);
    }

    @Override
    public List<Event> findByDay(Date day, int pageSize, int pageNum) {
        List<Event> storageEvents = storage.getEventStorage().values().stream()
                .filter(event -> java.sql.Date.valueOf(event.getDate()).equals(day))
                .collect(Collectors.toList());

        return getItems(pageSize, pageNum, storageEvents);
    }
}
