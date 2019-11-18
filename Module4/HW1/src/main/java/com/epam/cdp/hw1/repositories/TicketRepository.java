package com.epam.cdp.hw1.repositories;

import com.epam.cdp.hw1.model.Event;
import com.epam.cdp.hw1.model.Ticket;
import com.epam.cdp.hw1.model.User;

import java.util.List;

public interface TicketRepository extends IBaseRepository<Ticket, Long> {
    List<Ticket> findByUser(User user, int pageSize, int pageNum);

    List<Ticket> findByEvent(Event event, int pageSize, int pageNum);
}
