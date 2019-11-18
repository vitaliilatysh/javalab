package com.epam.cdp.hw4.services.base;

import com.epam.cdp.hw4.BaseTest;
import com.epam.cdp.hw4.models.*;
import com.epam.cdp.hw4.repositories.impl.base.EmployeeDao;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EmployeeServiceTest extends BaseTest {

    private Session session = entityManager.unwrap(Session.class);
    private EmployeeDao employeeDao = new EmployeeDao(session);
    private EmployeeService employeeService = new EmployeeService(employeeDao);

    @Test
    public void shouldFindById() {
        assertNotNull(employeeService.findById(12));
    }

    @Test
    public void shouldSaveEmployee() {
        Address address = new Address.AddressBuilder()
                .setCountry("Ukraine")
                .setState("Kharkiv state")
                .setCity("Kharkiv")
                .setAddressLine("Nauky ave, 14")
                .build();
        Employee employee = new Employee.EmployeeBuilder()
                .setName("Danylo")
                .setSurname("Markin")
                .setAddress(address)
                .setStatus(EmployeeStatus.HIRED)
                .build();

        employeeService.save(employee);

        assertNotNull(employeeService.findById(24));
    }

    @Test
    public void shouldUpdateEmployeeStatus() {
        Employee employee = employeeService.findById(13);
        employee.setStatus(EmployeeStatus.FIRED);
        employeeService.update(employee.getId());
        Employee updated = employeeService.findById(employee.getId());

        assertEquals(updated.getStatus(), EmployeeStatus.FIRED);

    }

    @Test
    public void shouldDeleteEmployee() {
        employeeService.delete(15);

        assertNull(employeeService.findById(15));
    }

    @Test
    public void shouldAddEmployeeToUnit() {
        employeeService.addEmployeeToUnit(employeeService.findById(12), 1);
        Employee employee = employeeService.findById(12);

        assertEquals(employee.getUnit().getId(), 1);
    }

    @Test
    public void shouldFindAllQa() {
        List<EmployeeQA> employeeQAList = employeeService.findAllQa();

        assertEquals(employeeQAList.size(), 6);
    }

    @Test
    public void shouldFindAllDev() {
        List<EmployeeDev> employeeDevLIst = employeeService.findAllDev();

        assertEquals(employeeDevLIst.size(), 2);
    }
}