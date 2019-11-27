package com.epam.cdp.module4.hw2.services;

import com.epam.cdp.module4.hw2.model.Event;
import com.epam.cdp.module4.hw2.model.Ticket;
import com.epam.cdp.module4.hw2.model.User;
import com.epam.cdp.module4.hw2.repositories.impl.TicketRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
