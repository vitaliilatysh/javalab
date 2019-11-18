package com.epam.cdp.hw4.services.base;

import com.epam.cdp.hw4.BaseTest;
import com.epam.cdp.hw4.models.Employee;
import com.epam.cdp.hw4.models.Project;
import com.epam.cdp.hw4.repositories.impl.base.EmployeeDao;
import com.epam.cdp.hw4.repositories.impl.base.ProjectDao;
import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectServiceTest extends BaseTest {

    private Session session = entityManager.unwrap(Session.class);
    private ProjectDao projectDao = new ProjectDao(session);
    private ProjectService projectService = new ProjectService(projectDao);

    private EmployeeDao employeeDao = new EmployeeDao(session);
    private EmployeeService employeeService = new EmployeeService(employeeDao);

    @Test
    public void shouldFindById() {
        assertNotNull(projectService.findById(11));
    }

    @Test
    public void shouldSaveProject() {
        Project project = new Project.ProjectBuilder()
                .setProjectName("iBank Systems")
                .build();

        projectService.save(project);
        assertNotNull(projectService.findById(44));
    }

    @Test
    public void shouldUpdateProject() {
        Project project = projectService.findById(11);
        project.setProjectName("JDK-MDK");

        projectService.update(project.getId());

        assertEquals(projectService.findById(project.getId()).getProjectName(), "JDK-MDK");
    }

    @Test
    public void shouldDeleteProject() {
        projectService.delete(22);
        assertNull(projectService.findById(22));
    }

    @Test
    public void shouldAssignEmployeeToProject() {
        Employee employee = employeeService.findById(12);
        projectService.assignEmployeeToProject(employee, 11);

        Project project = projectService.findById(11);

        assertEquals(project.getEmployees().size(), 1);
    }
}