package com.epam.cdp.hw4;

import com.epam.cdp.hw4.models.*;
import com.epam.cdp.hw4.services.EmployeeService;
import com.epam.cdp.hw4.services.ProjectService;
import com.epam.cdp.hw4.services.UnitService;

public class Application {
    public static void main(String[] args) {
        UnitService unitService = new UnitService();
        EmployeeService employeeService = new EmployeeService();
        ProjectService projectService = new ProjectService();

//        Unit unit = new Unit(1L, "Java Solutions");
//        unitService.save(unit);
//
//        Unit found = unitService.findById(1L);
//
//        found.setUnitName("AEM");
//        unitService.update(1L);

//        unitService.delete(1L);

//        Project project = new Project.ProjectBuilder()
//                .setId(1L)
//                .setProjectName("iBank Systems")
//                .build();

//        projectService.save(project);
        Employee foundEmployee =  employeeService.findById(1L);
        projectService.assignEmployeeToProject(foundEmployee, 1L);

//        EmployeeService employeeService = new EmployeeService();
//        Address address = new Address.AddressBuilder()
//                .setCountry("Ukraine")
//                .setState("Kharkiv state")
//                .setCity("Kharkiv")
//                .setAddressLine("Nauky ave, 14")
//                .build();
//        Employee employee = new Employee.EmployeeBuilder()
//                .setName("Danylo")
//                .setSurname("Markin")
//                .setAddress(address)
//                .setStatus(EmployeeStatus.HIRED)
//                .build();
//
//        employeeService.save(employee);

//        Employee foundEmployee =  employeeService.findById(1L);
//        employeeService.addEmployeeToUnit(foundEmployee, 1L);


    }
}
