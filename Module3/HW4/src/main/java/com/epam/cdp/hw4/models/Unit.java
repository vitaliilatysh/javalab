package com.epam.cdp.hw4.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Unit")
public class Unit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "unit_name")
    private String unitName;

    @OneToMany(targetEntity = Employee.class, mappedBy = "unit", fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();

    public Unit() {
    }

    public Unit(long id, String unitName) {
        this.id = id;
        this.unitName = unitName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public static class UnitBuilder{
        private Unit unit;

        public UnitBuilder(){
            unit = new Unit();
        }

        public UnitBuilder setId(long id){
            unit.id = id;
            return this;
        }

        public UnitBuilder setUnitName(String unitName){
            unit.unitName = unitName;
            return this;
        }

        public Unit build(){
            return unit;
        }
    }
}
