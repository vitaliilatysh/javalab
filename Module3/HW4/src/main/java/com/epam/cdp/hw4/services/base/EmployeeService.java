package com.epam.cdp.hw4.services.base;

import com.epam.cdp.hw4.models.Employee;
import com.epam.cdp.hw4.models.EmployeeDev;
import com.epam.cdp.hw4.models.EmployeeQA;
import com.epam.cdp.hw4.repositories.impl.base.EmployeeDao;
import com.epam.cdp.hw4.services.IBaseService;

import java.util.List;

public class EmployeeService implements IBaseService<Employee> {

    private static EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee findById(long id) {
        return employeeDao.findById(id);
    }

    @Override
    public void save(Employee entity) {
        employeeDao.save(entity);
    }

    @Override
    public void update(long id) {
        employeeDao.update(id);
    }

    @Override
    public void delete(long id) {
        employeeDao.delete(id);
    }

    public void addEmployeeToUnit(Employee employee, long unitId) {
        employeeDao.addToUnitById(employee, unitId);
    }

    public List<EmployeeQA> findAllQa(){
        return employeeDao.findAllQAs();
    }

    public List<EmployeeDev> findAllDev(){
        return employeeDao.findAllDevs();
    }
}
