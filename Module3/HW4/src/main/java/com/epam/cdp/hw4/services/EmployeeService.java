package com.epam.cdp.hw4.services;

import com.epam.cdp.hw4.models.Employee;
import com.epam.cdp.hw4.repositories.EmployeeDao;

public class EmployeeService {
    private static EmployeeDao employeeDao;

    public EmployeeService() {
        employeeDao = new EmployeeDao();
    }

    public Employee findById(long id) {
        employeeDao.openCurrentSession();
        Employee employee = employeeDao.findById(id);
        employeeDao.closeCurrentSession();
        return employee;
    }

    public void save(Employee entity) {
        employeeDao.openCurrentSessionwithTransaction();
        employeeDao.save(entity);
        employeeDao.closeCurrentSessionwithTransaction();
    }
}
