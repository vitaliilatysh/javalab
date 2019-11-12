package com.epam.cdp.hw4.models;

import javax.persistence.*;

@Entity
@DiscriminatorValue("qa")
public class EmployeeQA extends Employee {

    private String role;

    public EmployeeQA() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
