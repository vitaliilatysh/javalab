package com.epam.cdp.hw4.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "personal_info")
public class EmployeePersonalInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "hireDate")
    private Date hireDate;

    @Column(name = "fireDate")
    private Date fireDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public EmployeePersonalInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getFireDate() {
        return fireDate;
    }

    public void setFireDate(Date fireDate) {
        this.fireDate = fireDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public static class PersonalBuilder{
        private EmployeePersonalInfo employeePersonalInfo;

        public PersonalBuilder(){
            employeePersonalInfo = new EmployeePersonalInfo();
        }

        public PersonalBuilder setId(long id) {
            employeePersonalInfo.id = id;
            return this;
        }

        public PersonalBuilder setBirthDate(Date birthDate) {
            employeePersonalInfo.birthDate = birthDate;
            return this;
        }

        public PersonalBuilder setHireDate(Date hireDate) {
            employeePersonalInfo.hireDate = hireDate;
            return this;
        }

        public PersonalBuilder setFireDate(Date fireDate) {
            employeePersonalInfo.fireDate = fireDate;
            return this;
        }

        public EmployeePersonalInfo build(){
            return employeePersonalInfo;
        }
    }
}
