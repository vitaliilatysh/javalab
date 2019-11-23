package com.epam.cdp.module4.hw2.controller;

import com.epam.cdp.module4.hw2.facade.impl.BookingFacadeImpl;
import com.epam.cdp.module4.hw2.model.Event;
import com.epam.cdp.module4.hw2.model.User;
import com.epam.cdp.module4.hw2.model.impl.EventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private BookingFacadeImpl bookingFacade;

    /**
     * Get users by name
     *
     * @param userName user name
     * @param pageSize page size
     * @param page     page number
     * @param model    model
     * @return model view
     */
    @GetMapping(value = "/users/find", params = {"userName", "pageSize", "page"})
    public String getUserByName(
            @RequestParam String userName,
            @RequestParam int pageSize,
            @RequestParam int page,
            Model model) {
        List<User> byName = bookingFacade.getUsersByName(userName, pageSize, page);
        model.addAttribute("users", byName);
        return "users";
    }

    /**
     * Get event by id
     *
     * @param eventId event id
     * @param model   model
     * @return model view
     */
    @GetMapping(value = "/events/find", params = "eventId")
    public String getEventById(
            @RequestParam int eventId,
            Model model) {
        Event event = bookingFacade.getEventById(eventId);
        model.addAttribute("events", Collections.singleton(event));
        return "events";
    }

    /**
     * Get events by title
     *
     * @param eventTitle event title
     * @param pageSize   page size
     * @param page       page number
     * @param model      model
     * @return model view
     */
    @GetMapping(value = "/events/find", params = {"eventTitle", "pageSize", "page"})
    public String getEventsByTitle(
            @RequestParam String eventTitle,
            @RequestParam int pageSize,
            @RequestParam int page,
            Model model) {
        List<Event> events = bookingFacade.getEventsByTitle(eventTitle, pageSize, page);
        model.addAttribute("events", events);
        return "events";
    }

    /**
     * Get events by date
     *
     * @param eventDate event date
     * @param pageSize  page size
     * @param page      page number
     * @param model     model
     * @return model view
     */
    @GetMapping(value = "/events/find", params = {"eventDate", "pageSize", "page"})
    public String getEventByDate(
            @RequestParam String eventDate,
            @RequestParam int pageSize,
            @RequestParam int page,
            Model model) {
        List<Event> events = bookingFacade.getEventsForDay(Date.valueOf(eventDate), pageSize, page);
        model.addAttribute("events", events);
        return "events";
    }

    /**
     * Create event
     *
     * @param title event name
     * @param date  event date
     * @param model model
     * @return model view
     */
    @GetMapping(value = "/events/create", params = {"title", "date"})
    public String createEvent(
            @RequestParam String title,
            @RequestParam String date,
            Model model) {
        Event event = new EventImpl();
        event.setTitle(title);
        event.setDate(new java.sql.Date(Date.valueOf(date).getTime()).toLocalDate());

        Event result = bookingFacade.createEvent(event);
        model.addAttribute("events", Collections.singleton(result));
        return "events";
    }

    /**
     * Update event
     *
     * @param eventId event id
     * @param title   event title
     * @param date    event date
     * @param model   model
     * @return model view
     */
    @GetMapping(value = "/events/update", params = {"eventId", "title", "date"})
    public String updateEvent(
            @RequestParam int eventId,
            @RequestParam String title,
            @RequestParam String date,
            Model model) {
        Event event = new EventImpl();
        event.setId(eventId);
        event.setTitle(title);
        event.setDate(new java.sql.Date(Date.valueOf(date).getTime()).toLocalDate());

        Event result = bookingFacade.updateEvent(event);
        model.addAttribute("events", Collections.singleton(result));
        return "events";
    }

    /**
     * Delete event by id
     *
     * @param eventId event id
     * @param model   model
     * @return model view
     */
    @GetMapping(value = "/events/delete", params = {"eventId"})
    public String deleteEvent(
            @RequestParam int eventId,
            Model model) {

        boolean result = bookingFacade.deleteEvent(eventId);
        model.addAttribute("deleted", result);
        return "events";
    }

}
