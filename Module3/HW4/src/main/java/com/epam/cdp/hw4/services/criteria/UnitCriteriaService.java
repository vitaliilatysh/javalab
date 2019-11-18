package com.epam.cdp.hw4.services.criteria;

import com.epam.cdp.hw4.models.Unit;
import com.epam.cdp.hw4.repositories.impl.base.UnitDao;
import com.epam.cdp.hw4.repositories.impl.criteria.UnitCriteriaDao;
import com.epam.cdp.hw4.services.IBaseService;

public class UnitCriteriaService {

    private UnitCriteriaDao unitCriteriaDao;

    public UnitCriteriaService(UnitCriteriaDao unitCriteriaDao) {
        this.unitCriteriaDao = unitCriteriaDao;
    }

    public Unit findById(long id) {
        return unitCriteriaDao.findById(id);
    }

}

