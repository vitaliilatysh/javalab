package com.epam.cdp.hw4.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Project")
public class Project {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "project_name")
    private String projectName;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "employee_project",
            joinColumns = {@JoinColumn(name = "project_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "employee_id", nullable = false, updatable = false)}
    )
    private Set<Employee> employees = new HashSet<>();


    public Project() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public static class ProjectBuilder{
        private Project project;

        public ProjectBuilder(){
            project = new Project();
        }

        public ProjectBuilder setId(long id) {
            project.id = id;
            return this;
        }

        public ProjectBuilder setProjectName(String projectName) {
            project.projectName = projectName;
            return this;
        }

        public ProjectBuilder setEmployees(Set<Employee> employees) {
            project.employees = employees;
            return this;
        }

        public Project build(){
            return project;
        }
    }
}
