package com.epam.cdp.hw1.services;

import com.epam.cdp.hw1.repositories.impl.TicketRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketService {

    @Autowired
    private TicketRepositoryImpl ticketRepository;
}
