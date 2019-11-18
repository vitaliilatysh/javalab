package com.epam.cdp.hw4.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "personal_info")
public class PersonalInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "birthDate", columnDefinition = "DATE")
    private Date birthDate;

    @Column(name = "hireDate", columnDefinition = "DATE")
    private Date hireDate;

    @Column(name = "fireDate", columnDefinition = "DATE")
    private Date fireDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public PersonalInfo() {
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
        private PersonalInfo personalInfo;

        public PersonalBuilder(){
            personalInfo = new PersonalInfo();
        }

        public PersonalBuilder setId(long id) {
            personalInfo.id = id;
            return this;
        }

        public PersonalBuilder setBirthDate(Date birthDate) {
            personalInfo.birthDate = birthDate;
            return this;
        }

        public PersonalBuilder setHireDate(Date hireDate) {
            personalInfo.hireDate = hireDate;
            return this;
        }

        public PersonalBuilder setFireDate(Date fireDate) {
            personalInfo.fireDate = fireDate;
            return this;
        }

        public PersonalBuilder setEmployee(Employee employee){
            personalInfo.employee = employee;
            return this;
        }

        public PersonalInfo build(){
            return personalInfo;
        }
    }
}
