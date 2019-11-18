package com.epam.cdp.hw1.application;

import com.epam.cdp.hw1.facade.BookingFacade;
import com.epam.cdp.hw1.facade.impl.BookingFacadeImpl;
import com.epam.cdp.hw1.model.Event;
import com.epam.cdp.hw1.services.EventService;
import com.epam.cdp.hw1.storage.Storage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext( "beans.xml" );
        BookingFacade facade = context.getBean(BookingFacadeImpl.class);

        Event foundEvent = facade.getEventById(12);
    }
}
