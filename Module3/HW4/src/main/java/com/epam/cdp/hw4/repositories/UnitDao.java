package com.epam.cdp.hw4.repositories;

import com.epam.cdp.hw4.models.Address;
import com.epam.cdp.hw4.models.Employee;
import com.epam.cdp.hw4.models.Unit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UnitDao implements IEmployeeDao<Unit> {

    private Session currentSession;
    private Transaction currentTransaction;


    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Unit.class);
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Address.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
    @Override
    public void save(Unit entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Unit entity) {

    }

    @Override
    public Unit findById(long id) {
        return null;
    }

    @Override
    public void delete(Unit entity) {

    }

    @Override
    public List<Unit> findAll() {
        return null;
    }
}
