package com.epam.cdp.hw4;

import com.epam.cdp.hw4.models.Unit;
import com.epam.cdp.hw4.services.UnitService;

public class Application {
    public static void main(String[] args) {
        UnitService unitService = new UnitService();
        Unit unit = new Unit(1L, "Java Solutions");
        unitService.save(unit);
    }
}
