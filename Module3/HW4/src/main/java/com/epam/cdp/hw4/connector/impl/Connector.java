package com.epam.cdp.hw4.connector.impl;

import com.epam.cdp.hw4.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Connector {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Unit.class);
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(PersonalInfo.class);
        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(EmployeeQA.class);
        configuration.addAnnotatedClass(EmployeeDev.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
