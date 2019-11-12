package com.epam.cdp.hw4.services.hql;

import com.epam.cdp.hw4.models.Unit;
import com.epam.cdp.hw4.repositories.impl.hql.UnitHqlDao;

public class UnitHqlService {

    private UnitHqlDao unitHqlDao;

    public UnitHqlService() {
        unitHqlDao = new UnitHqlDao();
    }

    public Unit findById(long id) {
        return unitHqlDao.findById(id);
    }
}
