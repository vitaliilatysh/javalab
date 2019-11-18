package com.epam.cdp.hw1.facade.impl;

import com.epam.cdp.hw1.facade.BookingFacade;
import com.epam.cdp.hw1.model.Event;
import com.epam.cdp.hw1.model.Ticket;
import com.epam.cdp.hw1.model.User;
import com.epam.cdp.hw1.services.EventService;
import com.epam.cdp.hw1.services.TicketService;
import com.epam.cdp.hw1.services.UserService;

import java.util.Date;
import java.util.List;

public class BookingFacadeImpl implements BookingFacade {

    private EventService eventService;
    private TicketService ticketService;
    private UserService userService;

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
        return null;
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public Event createEvent(Event event) {
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return null;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return false;
    }

    @Override
    public User getUserById(long userId) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(long userId) {
        return false;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return false;
    }
}
