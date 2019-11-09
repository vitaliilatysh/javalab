package com.epam.cdp.hw4.services;

import com.epam.cdp.hw4.models.Employee;
import com.epam.cdp.hw4.repositories.impl.EmployeeDao;

public class EmployeeService {
    private static EmployeeDao employeeDao;

    public EmployeeService() {
        employeeDao = new EmployeeDao();
    }

    public Employee findById(long id) {
        return employeeDao.findById(id);
    }

    public void save(Employee entity) {
        employeeDao.save(entity);
    }

    public void update(long id){
        employeeDao.update(id);
    }

    public void delete(long id){
        employeeDao.delete(id);
    }

    public void addEmployeeToUnit(Employee employee, long unitId){
        employeeDao.addToUnitById(employee, unitId);
    }

}
