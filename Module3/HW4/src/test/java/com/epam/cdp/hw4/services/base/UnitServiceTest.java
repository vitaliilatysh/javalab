package com.epam.cdp.hw4.services.base;

import com.epam.cdp.hw4.BaseTest;
import com.epam.cdp.hw4.models.Unit;
import com.epam.cdp.hw4.repositories.impl.base.UnitDao;
import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitServiceTest extends BaseTest {

    private Session session = entityManager.unwrap(Session.class);
    private UnitDao unitDao = new UnitDao(session);
    private UnitService unitService = new UnitService(unitDao);

    @Test
    public void shouldReturnUnitById() {
        assertNotNull(unitService.findById(1));
    }

    @Test
    public void shouldSaveUnit() {
        Unit unit = new Unit.UnitBuilder()
                .setUnitName("AEM Department")
                .build();

        unitService.save(unit);
        assertNotNull(unitService.findById(1));
    }

    @Test
    public void shouldUpdateUnit() {
        Unit unit = unitService.findById(4);
        unit.setUnitName("Java Department");
        unitService.update(unit.getId());

        assertEquals(unit.getUnitName(), unitService.findById(4).getUnitName());
    }

    @Test
    public void shouldDeleteUnit() {
        unitService.delete(2);
        assertNull(unitService.findById(2));
    }
}