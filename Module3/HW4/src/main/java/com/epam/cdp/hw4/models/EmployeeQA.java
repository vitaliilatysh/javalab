package com.epam.cdp.hw4.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("qa")
public class EmployeeQA extends Employee {

    public EmployeeQA() {
    }

}
