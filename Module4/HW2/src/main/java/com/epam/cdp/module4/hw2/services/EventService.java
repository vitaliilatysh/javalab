package com.epam.cdp.module4.hw2.services;

import com.epam.cdp.module4.hw2.model.Event;
import com.epam.cdp.module4.hw2.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event findById(long id) {
        return eventRepository.findById(id);
    }

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getByTitle(String title, int pageSize, int pageNum) {
        return eventRepository.findByTitle(title, pageSize, pageNum);
    }

    /**
     * Update event
     *
     * @param event event entity
     * @return event
     */
    public Event update(Event event) {
        Event existed = findById(event.getId());

        if (existed == null) {
            throw new NoSuchElementException();
        }
        return eventRepository.update(event);
    }

    public boolean delete(long eventId) {
        return eventRepository.delete(eventId);
    }

    public List<Event> getByDay(Date day, int pageSize, int pageNum) {
        return eventRepository.findByDay(day, pageSize, pageNum);
    }
}
