package com.epam.cdp.hw4.services.criteria;

import com.epam.cdp.hw4.BaseTest;
import com.epam.cdp.hw4.repositories.impl.criteria.UnitCriteriaDao;
import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitCriteriaServiceTest extends BaseTest {

    private Session session = entityManager.unwrap(Session.class);
    private UnitCriteriaDao unitDao = new UnitCriteriaDao(session);
    private UnitCriteriaService unitService = new UnitCriteriaService(unitDao);

    @Test
    public void findById() {
        assertNotNull(unitService.findById(1));
    }
}