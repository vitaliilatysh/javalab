package com.epam.cdp.hw1.services;

import com.epam.cdp.hw1.model.Event;
import com.epam.cdp.hw1.model.Ticket;
import com.epam.cdp.hw1.model.User;
import com.epam.cdp.hw1.repositories.impl.TicketRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TicketService {

    @Autowired
    private TicketRepositoryImpl ticketRepository;

    public Ticket book(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public boolean cancel(long ticketId) {
        return ticketRepository.delete(ticketId);
    }

    public List<Ticket> getByUser(User user, int pageSize, int pageNum) {
        return ticketRepository.findByUser(user, pageSize, pageNum);
    }

    public List<Ticket> getByEvent(Event event, int pageSize, int pageNum) {
        return ticketRepository.findByEvent(event, pageSize, pageNum);
    }
}
