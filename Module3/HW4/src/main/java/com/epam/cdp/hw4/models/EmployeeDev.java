package com.epam.cdp.hw4.models;

import javax.persistence.*;

@Entity
@DiscriminatorValue("dev")
public class    EmployeeDev {

    private String role;

    public EmployeeDev() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
