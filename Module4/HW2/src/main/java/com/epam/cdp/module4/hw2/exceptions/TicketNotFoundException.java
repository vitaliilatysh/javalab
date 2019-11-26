package com.epam.cdp.module4.hw2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Ticket Not Found")
public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(int id) {
        super("Ticket not found with id=" + id);
    }
}
