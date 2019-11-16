package com.epam.cdp.hw4.repositories.impl.base;

import com.epam.cdp.hw4.models.Employee;
import com.epam.cdp.hw4.models.EmployeeDev;
import com.epam.cdp.hw4.models.EmployeeQA;
import com.epam.cdp.hw4.models.Unit;
import com.epam.cdp.hw4.repositories.IBaseDao;
import org.hibernate.Session;

import java.util.List;

public class EmployeeDao implements IBaseDao<Employee> {

    private Session session;

    public EmployeeDao(Session session) {
        this.session = session;
    }

    @Override
    public void save(Employee entity) {
        session.beginTransaction();

        session.save(entity);

        session.getTransaction().commit();
    }

    @Override
    public void update(long id) {
        session.beginTransaction();

        Employee employee = session.get(Employee.class, id);
        session.update(employee);

        session.getTransaction().commit();
    }

    @Override
    public Employee findById(long id) {
        session.beginTransaction();

        Employee employee = session.get(Employee.class, id);

        session.getTransaction().commit();

        return employee;
    }

    @Override
    public void delete(long id) {
        session.beginTransaction();

        Employee employee = session.get(Employee.class, id);
        session.delete(employee);

        session.getTransaction().commit();
    }

    /**
     * Add employee to specific unit
     * @param employee employee
     * @param unitId unit id
     */
    public void addToUnitById(Employee employee, long unitId) {
        session.beginTransaction();

        long employeeId = employee.getId();
        Employee foundEmployee = session.get(Employee.class, employeeId);

        Unit foundUnit = session.get(Unit.class, unitId);
        foundEmployee.setUnit(foundUnit);
        session.update(employee);

        session.getTransaction().commit();
    }

    /**
     * Return all employees on qa positions
     * @return qa employees
     */
    public List<EmployeeQA> findAllQAs() {
        session.beginTransaction();
        List<EmployeeQA> employeeQAS = (List<EmployeeQA>)session.createCriteria(EmployeeQA.class).list();
        session.getTransaction().commit();
        return employeeQAS;
    }

    /**
     * Return all employees on dev positions
     * @return dev employees
     */
    public List<EmployeeDev> findAllDevs() {
        session.beginTransaction();
        List<EmployeeDev> employeeDevs = (List<EmployeeDev>)session.createCriteria(EmployeeDev.class).list();
        session.getTransaction().commit();
        return employeeDevs;
    }
}
