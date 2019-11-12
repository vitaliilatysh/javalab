package com.epam.cdp.hw4.repositories.impl.hql;

import com.epam.cdp.hw4.connector.Connector;
import com.epam.cdp.hw4.models.Unit;
import com.epam.cdp.hw4.repositories.impl.base.UnitDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;


public class UnitHqlDao extends UnitDao {

    private Session session;

    public UnitHqlDao() {
        session = Connector.getSessionFactory().openSession();
    }

    @Override
    public Unit findById(long id) {
        session.beginTransaction();

        Query query = session.createQuery("from Unit where id = :id");
        query.setParameter("id", id);
        Object result = query.list().get(0);

        session.getTransaction().commit();

        if (result == null) {
            return null;
        }

        return (Unit) result;
    }

}
