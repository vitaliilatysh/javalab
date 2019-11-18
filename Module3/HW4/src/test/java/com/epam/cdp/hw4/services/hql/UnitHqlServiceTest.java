package com.epam.cdp.hw4.services.hql;

import com.epam.cdp.hw4.BaseTest;
import com.epam.cdp.hw4.repositories.impl.hql.UnitHqlDao;
import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitHqlServiceTest extends BaseTest {

    private Session session = entityManager.unwrap(Session.class);
    private UnitHqlDao unitDao = new UnitHqlDao(session);
    private UnitHqlService unitService = new UnitHqlService(unitDao);

    @Test
    public void findById() {
        assertNotNull(unitService.findById(1));
    }
}