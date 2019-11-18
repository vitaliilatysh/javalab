package com.epam.cdp.hw4.services.base;

import com.epam.cdp.hw4.models.Employee;
import com.epam.cdp.hw4.models.Project;
import com.epam.cdp.hw4.repositories.impl.base.ProjectDao;
import com.epam.cdp.hw4.services.IBaseService;

public class ProjectService implements IBaseService<Project> {
    private static ProjectDao projectDao;

    public ProjectService(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public Project findById(long id) {
        return projectDao.findById(id);
    }

    @Override
    public void save(Project entity) {
        projectDao.save(entity);
    }

    @Override
    public void update(long id) {
        projectDao.update(id);
    }

    @Override
    public void delete(long id) {
        projectDao.delete(id);
    }

    public void assignEmployeeToProject(Employee employee, long projectId) {
        projectDao.assignToProjectById(employee, projectId);
    }
}
