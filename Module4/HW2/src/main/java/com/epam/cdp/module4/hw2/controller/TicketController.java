package com.epam.cdp.module4.hw2.controller;

import com.epam.cdp.module4.hw2.exceptions.EventNotFoundException;
import com.epam.cdp.module4.hw2.exceptions.TicketNotFoundException;
import com.epam.cdp.module4.hw2.exceptions.UserNotFoundException;
import com.epam.cdp.module4.hw2.facade.impl.BookingFacadeImpl;
import com.epam.cdp.module4.hw2.model.Event;
import com.epam.cdp.module4.hw2.model.Ticket;
import com.epam.cdp.module4.hw2.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private static Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private BookingFacadeImpl bookingFacade;

    /**
     * Get ticket by event
     *
     * @param eventId  event id
     * @param pageSize page size
     * @param page     page number
     * @param model    model
     * @return model view
     */
    @GetMapping(params = {"eventId", "pageSize", "page"})
    public String getTicketByEvent(
            @RequestParam(value = "eventId") int eventId,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "page") int page,
            Model model) {
        Event event = bookingFacade.getEventById(eventId);
        if (event == null) {
            throw new EventNotFoundException(eventId);
        }
        List<Ticket> result = bookingFacade.getBookedTickets(event, pageSize, page);
        model.addAttribute("tickets", result);
        return "tickets";
    }

    /**
     * Get ticket by user
     *
     * @param userId   user id
     * @param pageSize page size
     * @param page     page number
     * @param model    model
     * @return model view
     */
    @GetMapping(params = {"userId", "pageSize", "page"})
    public String getTicketByUser(
            @RequestParam(value = "userId") int userId,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "page") int page,
            Model model) {
        User user = bookingFacade.getUserById(userId);
        if (user == null) {
            throw new EventNotFoundException(userId);
        }
        List<Ticket> result = bookingFacade.getBookedTickets(user, pageSize, page);
        model.addAttribute("tickets", result);
        return "tickets";
    }


    /**
     * Book ticket
     *
     * @param userId   user id
     * @param eventId  event id
     * @param place    place
     * @param category category
     * @param model    model
     * @return model view
     */
    @GetMapping(value = "/book", params = {"userId", "eventId", "place", "category"})
    public String bookTicket(
            @RequestParam(value = "userId") int userId,
            @RequestParam(value = "eventId") int eventId,
            @RequestParam(value = "place") int place,
            @RequestParam(value = "category") String category,
            Model model) {

        User user = bookingFacade.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        Event event = bookingFacade.getEventById(eventId);
        if (event == null) {
            throw new EventNotFoundException(eventId);
        }

        Ticket result = bookingFacade.bookTicket(userId, eventId, place, Ticket.Category.valueOf(category));
        model.addAttribute("tickets", Collections.singleton(result));
        return "tickets";
    }

    /**
     * Cancel ticket by id
     *
     * @param id    ticket id
     * @param model model
     * @return model view
     */
    @GetMapping(value = "/cancel", params = {"id"})
    public String cancelTicket(@RequestParam(value = "id") int id, Model model) {
        boolean result = bookingFacade.cancelTicket(id);
        if (!result) {
            throw new TicketNotFoundException(id);
        }
        model.addAttribute("deleted", result);
        return "tickets";
    }

}
