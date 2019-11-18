package com.epam.cdp.hw1.repositories.impl;

import com.epam.cdp.hw1.model.Ticket;
import com.epam.cdp.hw1.repositories.IBaseRepository;
import com.epam.cdp.hw1.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketRepositoryImpl implements IBaseRepository<Ticket, Long> {

    @Autowired
    private Storage storage;

    @Override
    public Ticket save(Ticket entity) {
        return null;
    }

    @Override
    public Ticket findById(Long aLong) {
        return null;
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
