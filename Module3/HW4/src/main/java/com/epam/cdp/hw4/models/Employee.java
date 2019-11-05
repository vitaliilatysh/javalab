package com.epam.cdp.hw4.models;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "Employee")
public class Employee {

    @Enumerated(STRING)
    public EmployeeStatus status;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private Date hireDate;
    private Date fireDate;
    private Address address;
}
