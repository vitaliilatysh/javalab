package com.epam.cdp.module4.hw2.repositories.impl;

import com.epam.cdp.module4.hw2.model.Event;
import com.epam.cdp.module4.hw2.repositories.EventRepository;
import com.epam.cdp.module4.hw2.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.hash;

@Repository
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private Storage storage;

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Event save(Event entity) {
        Map<String, Event> eventStorage = storage.getEventStorage();

        int id = Math.abs(hash(entity.getTitle(), entity.getDate()));
        String newEntryId = String.valueOf(id);
        entity.setId(id);
        eventStorage.put(newEntryId, entity);
        return eventStorage.get(newEntryId);
    }

    @Override
    public Event findById(Long id) {
        return storage.getEventStorage().get(Long.toString(id));
    }

    @Override
    public Event update(Event entity) {
        String updatedEntityId = String.valueOf(entity.getId());

        storage.getEventStorage().put(updatedEntityId, entity);

        return storage.getEventStorage().get(updatedEntityId);
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
