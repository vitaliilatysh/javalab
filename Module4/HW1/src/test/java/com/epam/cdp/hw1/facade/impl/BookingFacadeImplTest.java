package com.epam.cdp.hw1.facade.impl;

import com.epam.cdp.hw1.facade.BookingFacade;
import com.epam.cdp.hw1.model.Event;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class BookingFacadeImplTest {

    private static ApplicationContext context;
    private static BookingFacade facade;

    @BeforeClass
    public static void setUp(){
        context = new ClassPathXmlApplicationContext( "beans.xml" );
        facade = context.getBean(BookingFacadeImpl.class);
    }


    @Test
    public void getEventById() {
        assertNotNull(facade.getEventById(12));
    }

    @Test
    public void getEventsByTitle() {
    }

    @Test
    public void getEventsForDay() {
    }

    @Test
    public void createEvent() {
    }

    @Test
    public void updateEvent() {
    }

    @Test
    public void deleteEvent() {
    }

    @Test
    public void getUserById() {
    }

    @Test
    public void getUserByEmail() {
    }

    @Test
    public void getUsersByName() {
    }

    @Test
    public void createUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void bookTicket() {
    }

    @Test
    public void getBookedTickets() {
    }

    @Test
    public void getBookedTickets1() {
    }

    @Test
    public void cancelTicket() {
    }
}