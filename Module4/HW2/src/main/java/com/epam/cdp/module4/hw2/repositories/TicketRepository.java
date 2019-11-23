package com.epam.cdp.module4.hw2.repositories;

import com.epam.cdp.module4.hw2.model.Event;
import com.epam.cdp.module4.hw2.model.Ticket;
import com.epam.cdp.module4.hw2.model.User;

import java.util.List;

public interface TicketRepository extends IBaseRepository<Ticket, Long> {
    List<Ticket> findByUser(User user, int pageSize, int pageNum);

    List<Ticket> findByEvent(Event event, int pageSize, int pageNum);
}
