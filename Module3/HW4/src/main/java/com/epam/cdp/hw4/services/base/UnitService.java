package com.epam.cdp.hw4.services.base;

import com.epam.cdp.hw4.models.Unit;
import com.epam.cdp.hw4.repositories.impl.base.UnitDao;
import com.epam.cdp.hw4.services.IBaseService;

public class UnitService implements IBaseService<Unit> {

    private UnitDao unitDao;

    public UnitService() {
        unitDao = new UnitDao();
    }

    @Override
    public Unit findById(long id) {
        return unitDao.findById(id);
    }

    @Override
    public void save(Unit entity) {
        unitDao.save(entity);
    }

    @Override
    public void update(long id) {
        unitDao.update(id);
    }

    @Override
    public void delete(long id) {
        unitDao.delete(id);
    }
}

