package com.epam.cdp.hw4.repositories.impl.criteria;

import com.epam.cdp.hw4.connector.Connector;
import com.epam.cdp.hw4.models.Unit;
import com.epam.cdp.hw4.repositories.impl.base.UnitDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UnitCriteriaDao extends UnitDao {

    private Session session;

    public UnitCriteriaDao() {
        session = Connector.getSessionFactory().openSession();
    }

    @Override
    public Unit findById(long id) {
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Unit.class)
                .add(Restrictions.eq("id", id));

        Object result = criteria.uniqueResult();

        session.getTransaction().commit();

        if (result == null) {
            return null;
        }

        return (Unit) result;
    }

}
