package com.epam.cdp.hw4.repositories.impl.base;

import com.epam.cdp.hw4.models.PersonalInfo;
import com.epam.cdp.hw4.repositories.IBaseDao;
import org.hibernate.Session;

public class PersonalInfoDao implements IBaseDao<PersonalInfo> {

    private Session session;

    public PersonalInfoDao(Session session) {
        this.session = session;
    }

    @Override
    public void save(PersonalInfo entity) {
        session.beginTransaction();

        session.save(entity);

        session.getTransaction().commit();
    }

    @Override
    public void update(long id) {
        session.beginTransaction();

        PersonalInfo personalInfo = session.get(PersonalInfo.class, id);
        session.update(personalInfo);

        session.getTransaction().commit();
    }

    @Override
    public PersonalInfo findById(long id) {
        session.beginTransaction();

        PersonalInfo personalInfo = session.get(PersonalInfo.class, id);

        session.getTransaction().commit();

        return personalInfo;
    }

    @Override
    public void delete(long id) {
        session.beginTransaction();

        PersonalInfo personalInfo = session.get(PersonalInfo.class, id);
        session.delete(personalInfo);

        session.getTransaction().commit();
    }
}
