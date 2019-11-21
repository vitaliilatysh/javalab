package com.epam.cdp.hw1.repositories.impl;

import com.epam.cdp.hw1.model.Event;
import com.epam.cdp.hw1.model.Ticket;
import com.epam.cdp.hw1.model.User;
import com.epam.cdp.hw1.repositories.TicketRepository;
import com.epam.cdp.hw1.storage.Storage;

import java.util.*;
import java.util.function.Function;
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
        Map<Long, List<Ticket>> sortedByEventDateDesc = storage.getEventStorage().values().stream()
                .sorted(Comparator.comparing(Event::getDate))
                .collect(
                        LinkedHashMap::new,
                        (map, event) -> map.put(event.getId(), new ArrayList<>()),
                        Map::putAll);

        storage.getTicketStorage().values().stream()
                .filter(ticket -> ticket.getUserId() == user.getId())
                .filter(ticket -> sortedByEventDateDesc.containsKey(ticket.getEventId()))
                .forEach(
                        ticket -> sortedByEventDateDesc.get(ticket.getEventId()).add(ticket)
                );

        List<Ticket> result = sortedByEventDateDesc.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return getItems(pageSize, pageNum, result);
    }

    @Override
    public List<Ticket> findByEvent(Event event, int pageSize, int pageNum) {
        Map<Long, List<Ticket>> sortedByUserEmailAsc = storage.getUserStorage().values().stream()
                .sorted(Comparator.comparing(User::getEmail))
                .collect(
                        LinkedHashMap::new,
                        (map, user) -> map.put(user.getId(), new ArrayList<>()),
                        Map::putAll);

        storage.getTicketStorage().values().stream()
                .filter(ticket -> ticket.getEventId() == event.getId())
                .filter(ticket -> sortedByUserEmailAsc.containsKey(ticket.getUserId()))
                .forEach(
                        ticket -> sortedByUserEmailAsc.get(ticket.getUserId()).add(ticket)
                );

        List<Ticket> result = sortedByUserEmailAsc.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return getItems(pageSize, pageNum, result);
    }
}
