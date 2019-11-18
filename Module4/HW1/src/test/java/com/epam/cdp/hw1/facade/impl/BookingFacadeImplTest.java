package com.epam.cdp.hw1.facade.impl;

import com.epam.cdp.hw1.facade.BookingFacade;
import com.epam.cdp.hw1.model.Event;
import com.epam.cdp.hw1.model.Ticket;
import com.epam.cdp.hw1.model.User;
import com.epam.cdp.hw1.model.impl.EventImpl;
import com.epam.cdp.hw1.model.impl.TicketImpl;
import com.epam.cdp.hw1.model.impl.UserImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class BookingFacadeImplTest {

    private BookingFacade facade;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        facade = context.getBean(BookingFacadeImpl.class);
    }

    @Test
    public void getEventById() {
        assertNotNull(facade.getEventById(12));
    }

    @Test
    public void getEventsByTitle() {
        assertEquals(facade.getEventsByTitle("Tour", 0, 0).size(), 3);
    }

    @Test
    public void getEventsForDay() {
        assertEquals((facade.getEventsForDay(new GregorianCalendar(2019, Calendar.NOVEMBER, 9).getTime(), 0, 0)).size(), 2);
    }

    @Test
    public void createEvent() {
        Event event = new EventImpl();
        event.setId(3);
        event.setTitle("Nickelback Amsterdam Arena");
        event.setDate(LocalDate.of(2019, 12, 1));

        assertNotNull(facade.createEvent(event));
    }

    @Test
    public void updateEvent() {
        Event event = new EventImpl();
        event.setId(12);
        event.setTitle("Nickelback Amsterdam Arena");
        event.setDate(LocalDate.of(2019, 12, 1));

        facade.updateEvent(event);

        assertEquals(event, facade.getEventById(12));
    }

    @Test
    public void deleteEvent() {
        assertTrue(facade.deleteEvent(12));
    }

    @Test
    public void getUserById() {
        assertNotNull(facade.getUserById(1));
    }

    @Test
    public void getUserByEmail() {
        assertEquals(facade.getUserByEmail("andrii_manzhos@gmail.com").getEmail(), "andrii_manzhos@gmail.com");
    }

    @Test
    public void getUsersByName() {
        assertEquals(facade.getUsersByName("Andrii", 0, 0).size(), 1);
    }

    @Test
    public void createUser() {
        User user = new UserImpl();
        user.setId(3);
        user.setName("Paul");
        user.setEmail("paul_darten@gmail.com");

        assertNotNull(facade.createUser(user));
    }

    @Test
    public void updateUser() {
        User user = new UserImpl();
        user.setId(1);
        user.setName("Paul");
        user.setEmail("paul_darten@gmail.com");

        facade.updateUser(user);

        assertEquals(user, facade.getUserById(1));
    }

    @Test
    public void deleteUser() {
        assertTrue(facade.deleteUser(1));
    }

    @Test
    public void bookTicket() {
        assertNotNull(facade.bookTicket(113, 12, 555, Ticket.Category.STANDARD));
    }

    @Test
    public void getBookedTicketsByUser() {
        User user = facade.getUserById(1);
        assertEquals(facade.getBookedTickets(user, 0, 0).size(), 2);
    }

    @Test
    public void getBookedTicketsByEvent() {
        Event event = facade.getEventById(12);
        assertEquals(facade.getBookedTickets(event, 0, 0).size(), 1);
    }

    @Test
    public void cancelTicket() {
        assertTrue(facade.cancelTicket(111));
    }
}