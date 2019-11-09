package com.epam.cdp.hw4.repositories.impl;

import com.epam.cdp.hw4.connector.Connector;
import com.epam.cdp.hw4.models.Unit;
import com.epam.cdp.hw4.repositories.IBaseDao;
import org.hibernate.Session;

public class UnitDao extends Connector implements IBaseDao<Unit> {

    private Session session;

    public UnitDao() {
        session = Connector.getSessionFactory().openSession();
    }

    @Override
    public void save(Unit entity) {
        session.beginTransaction();

        session.save(entity);

        session.getTransaction().commit();
    }

    @Override
    public void update(long id) {
        session.beginTransaction();

        Unit unit = session.get(Unit.class, id);
        session.update(unit);

        session.getTransaction().commit();
    }

    @Override
    public Unit findById(long id) {
        session.beginTransaction();

        Unit unit = session.get(Unit.class, id);

        session.getTransaction().commit();

        return unit;
    }

    @Override
    public void delete(long id) {
        session.beginTransaction();

        Unit unit = session.get(Unit.class, id);
        session.delete(unit);

        session.getTransaction().commit();
    }
}
