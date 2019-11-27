package com.epam.cdp.module4.hw2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Event Not Found")
public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(int id) {
        super("Event not found with id=" + id);
    }
}
