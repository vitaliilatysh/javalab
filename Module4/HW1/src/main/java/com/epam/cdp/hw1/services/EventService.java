package com.epam.cdp.hw1.services;

import com.epam.cdp.hw1.model.Event;
import com.epam.cdp.hw1.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event findById(long id){
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event){
        return eventRepository.save(event);
    }


}
