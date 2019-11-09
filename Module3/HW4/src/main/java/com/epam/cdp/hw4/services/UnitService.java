package com.epam.cdp.hw4.services;

import com.epam.cdp.hw4.models.Unit;
import com.epam.cdp.hw4.repositories.impl.UnitDao;

public class UnitService {

    private UnitDao unitDao;

    public UnitService() {
        unitDao = new UnitDao();
    }

    public Unit findById(long id) {
        return unitDao.findById(id);
    }

    public void save(Unit entity) {
        unitDao.save(entity);
    }

    public void update(long id){
        unitDao.update(id);
    }

    public void delete(long id){
        unitDao.delete(id);
    }
}

