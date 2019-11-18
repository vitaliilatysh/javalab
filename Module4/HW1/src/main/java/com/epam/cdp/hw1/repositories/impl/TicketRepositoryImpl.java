package com.epam.cdp.hw1.repositories.impl;

import com.epam.cdp.hw1.model.Event;
import com.epam.cdp.hw1.model.Ticket;
import com.epam.cdp.hw1.model.User;
import com.epam.cdp.hw1.repositories.TicketRepository;
import com.epam.cdp.hw1.storage.Storage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketRepositoryImpl implements TicketRepository {

    private Storage storage;

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Ticket save(Ticket entity) {
        String newEntryId = String.valueOf(entity.getId());
        Ticket ticket = storage.getTicketStorage().get(newEntryId);
        if (ticket != null) {
            return ticket;
        }
        storage.getTicketStorage().put(newEntryId, entity);

        return storage.getTicketStorage().get(newEntryId);
    }

    @Override
    public Ticket findById(Long id) {
        return null;
    }

    @Override
    public Ticket update(Ticket entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Ticket ticket = storage.getTicketStorage().remove(String.valueOf(id));
        return ticket != null;
    }

    @Override
    public List<Ticket> findByUser(User user, int pageSize, int pageNum) {
         return storage.getTicketStorage().values().stream()
                .filter(userValue -> userValue.getUserId() == user.getId())
                 .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findByEvent(Event event, int pageSize, int pageNum) {
        return storage.getTicketStorage().values().stream()
                .filter(eventValue -> eventValue.getEventId() == event.getId())
                .collect(Collectors.toList());
    }
}
