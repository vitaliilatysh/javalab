package com.epam.cdp.hw4.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("dev")
public class EmployeeDev extends Employee {

    public EmployeeDev() {
    }

}
