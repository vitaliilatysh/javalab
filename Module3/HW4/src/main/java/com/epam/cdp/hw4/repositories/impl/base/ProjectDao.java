package com.epam.cdp.hw4.repositories.impl.base;

import com.epam.cdp.hw4.connector.Connector;
import com.epam.cdp.hw4.models.Employee;
import com.epam.cdp.hw4.models.Project;
import com.epam.cdp.hw4.repositories.IBaseDao;
import org.hibernate.Session;


public class ProjectDao implements IBaseDao<Project> {

    private Session session;

    public ProjectDao() {
        session = Connector.getSessionFactory().openSession();
    }

    @Override
    public void save(Project entity) {
        session.beginTransaction();

        session.save(entity);

        session.getTransaction().commit();
    }

    @Override
    public void update(long id) {
        session.beginTransaction();

        Project project = session.get(Project.class, id);
        session.update(project);

        session.getTransaction().commit();
    }

    @Override
    public Project findById(long id) {
        session.beginTransaction();

        Project project = session.get(Project.class, id);

        session.getTransaction().commit();

        return project;
    }

    @Override
    public void delete(long id) {
        session.beginTransaction();

        Project project = session.get(Project.class, id);
        session.delete(project);

        session.getTransaction().commit();
    }

    public void assignToProjectById(Employee employee, long projectId) {
        session.beginTransaction();

        long employeeId = employee.getId();
        Employee foundEmployee = session.get(Employee.class, employeeId);

        Project foundProject = session.get(Project.class, projectId);
        foundProject.getEmployees().add(foundEmployee);
        session.save(foundProject);

        session.getTransaction().commit();
    }

}
