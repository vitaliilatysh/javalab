package com.epam.cdp.hw4.services;

import com.epam.cdp.hw4.models.Employee;
import com.epam.cdp.hw4.models.Project;
import com.epam.cdp.hw4.repositories.impl.ProjectDao;

public class ProjectService {
    private static ProjectDao projectDao;

    public ProjectService() {
        projectDao = new ProjectDao();
    }

    public Project findById(long id) {
        return projectDao.findById(id);
    }

    public void save(Project entity) {
        projectDao.save(entity);
    }

    public void update(long id){
        projectDao.update(id);
    }

    public void delete(long id){
        projectDao.delete(id);
    }

    public void assignEmployeeToProject(Employee employee, long projectId){
        projectDao.assignToProjectById(employee, projectId);
    }
}
