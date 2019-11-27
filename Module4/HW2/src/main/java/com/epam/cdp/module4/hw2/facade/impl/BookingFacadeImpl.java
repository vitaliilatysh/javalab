package com.epam.cdp.module4.hw2.facade.impl;

import com.epam.cdp.module4.hw2.facade.BookingFacade;
import com.epam.cdp.module4.hw2.model.Event;
import com.epam.cdp.module4.hw2.model.Ticket;
import com.epam.cdp.module4.hw2.model.User;
import com.epam.cdp.module4.hw2.model.impl.TicketImpl;
import com.epam.cdp.module4.hw2.services.EventService;
import com.epam.cdp.module4.hw2.services.TicketService;
import com.epam.cdp.module4.hw2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingFacadeImpl implements BookingFacade {

    @Autowired
    private EventService eventService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    /**
     * Constructor BookingFacade
     *
     * @param userService   userService bean
     * @param ticketService ticketService bean
     * @param eventService  eventService bean
     */
    public BookingFacadeImpl(UserService userService, TicketService ticketService, EventService eventService) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.eventService = eventService;
    }

    @Override
    public Event getEventById(long eventId) {
        return eventService.findById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.getByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventService.getByDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        return eventService.create(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventService.update(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventService.delete(eventId);
    }

    @Override
    public User getUserById(long userId) {
        return userService.findById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.findByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        return userService.create(user);
    }

    @Override
    public User updateUser(User user) {
        return userService.update(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userService.delete(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Ticket ticket = new TicketImpl();
        ticket.setId(1);
        ticket.setUserId(userId);
        ticket.setEventId(eventId);
        ticket.setCategory(category);
        ticket.setPlace(place);
        return ticketService.book(ticket);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketService.getByUser(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketService.getByEvent(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketService.cancel(ticketId);
    }
}
