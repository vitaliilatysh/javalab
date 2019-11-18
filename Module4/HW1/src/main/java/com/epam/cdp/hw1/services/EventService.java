package com.epam.cdp.hw1.services;

import com.epam.cdp.hw1.model.Event;
import com.epam.cdp.hw1.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


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

    public Event update(Event event) {
        return eventRepository.update(event);
    }

    public boolean delete(long eventId) {
        return eventRepository.delete(eventId);
    }

    public List<Event> getByDay(Date day, int pageSize, int pageNum) {
        return eventRepository.findByDay(day, pageSize, pageNum);
    }
}
