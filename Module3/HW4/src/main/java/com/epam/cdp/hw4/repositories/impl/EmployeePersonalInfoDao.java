package com.epam.cdp.hw4.repositories.impl;

import com.epam.cdp.hw4.connector.Connector;
import com.epam.cdp.hw4.models.EmployeePersonalInfo;
import com.epam.cdp.hw4.repositories.IBaseDao;
import org.hibernate.Session;

public class EmployeePersonalInfoDao implements IBaseDao<EmployeePersonalInfo> {

    private Session session;

    public EmployeePersonalInfoDao() {
        session = Connector.getSessionFactory().openSession();
    }

    @Override
    public void save(EmployeePersonalInfo entity) {
        session.beginTransaction();

        session.save(entity);

        session.getTransaction().commit();
    }

    @Override
    public void update(long id) {
        session.beginTransaction();

        EmployeePersonalInfo employeePersonalInfo = session.get(EmployeePersonalInfo.class, id);
        session.update(employeePersonalInfo);

        session.getTransaction().commit();
    }

    @Override
    public EmployeePersonalInfo findById(long id) {
        session.beginTransaction();

        EmployeePersonalInfo employeePersonalInfo = session.get(EmployeePersonalInfo.class, id);

        session.getTransaction().commit();

        return employeePersonalInfo;
    }

    @Override
    public void delete(long id) {
        session.beginTransaction();

        EmployeePersonalInfo employeePersonalInfo = session.get(EmployeePersonalInfo.class, id);
        session.delete(employeePersonalInfo);

        session.getTransaction().commit();
    }
}
