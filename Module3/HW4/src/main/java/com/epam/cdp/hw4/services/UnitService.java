package com.epam.cdp.hw4.services;

import com.epam.cdp.hw4.models.Unit;
import com.epam.cdp.hw4.repositories.UnitDao;

public class UnitService {
    private static UnitDao unitDao;

    public UnitService() {
        unitDao = new UnitDao();
    }

    public Unit findById(long id) {
        unitDao.openCurrentSession();
        Unit unit = unitDao.findById(id);
        unitDao.closeCurrentSession();
        return unit;
    }

    public void save(Unit entity) {
        unitDao.openCurrentSessionwithTransaction();
        unitDao.save(entity);
        unitDao.closeCurrentSessionwithTransaction();
    }
}

