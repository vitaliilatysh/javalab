package com.epam.cdp.hw4.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "Employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "employee_name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Embedded
    private Address address;

    @Column(name = "status")
    @Enumerated(STRING)
    private EmployeeStatus status;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @OneToOne(mappedBy = "employee")
    private PersonalInfo personalInfo;

    @ManyToMany(mappedBy = "employees")
    private Set<Project> projects = new HashSet<>();

    public Employee(){

    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public static class EmployeeBuilder {
        private Employee employee;

        public EmployeeBuilder() {
            employee = new Employee();
        }

        public EmployeeBuilder setId(long id) {
            employee.id = id;
            return this;
        }

        public EmployeeBuilder setName(String name) {
            employee.name = name;
            return this;
        }

        public EmployeeBuilder setSurname(String surname) {
            employee.surname = surname;
            return this;
        }

        public EmployeeBuilder setAddress(Address address) {
            employee.address = address;
            return this;
        }

        public EmployeeBuilder setStatus(EmployeeStatus status) {
            employee.status = status;
            return this;
        }

        public Employee build() {
            return employee;
        }
    }
}
