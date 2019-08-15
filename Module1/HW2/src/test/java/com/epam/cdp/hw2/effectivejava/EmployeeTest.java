package com.epam.cdp.hw2.effectivejava;

import com.google.common.collect.Lists;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class EmployeeTest {

    // Section 2. Item 2: Consider a builder when faced with many constructor
    //parameters
    private Employee employee = Employee.newBuilder()
            .setFirstName("Dan")
            .setLastName("Brown")
            .setBirthDay(new Date(2000-10-10))
            .setHomeTown("London")
            .build();

    private Employee employee2 = Employee.newBuilder()
            .setFirstName("Carol")
            .setLastName("Sally")
            .setBirthDay(new Date(2002-9-11))
            .setHomeTown("New York")
            .build();

    @Test
    public void shouldCreateObjectWithFields(){
        assertNotNull(employee);
        assertEquals(employee.getFirstName(), "Dan");
        assertEquals(employee.getLastName(), "Brown");
        assertEquals(employee.getHomeTown(), "London");
        assertNotNull(employee.getBirthDay());
    }

    //Section 8. Item 49: Check parameters for validity
    //Section 10. Item 69: Use exceptions only for exceptional conditions
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldThrowExceptionIfFirstNameIsNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Name cannot be null");

        Employee.newBuilder()
                .setFirstName(null)
                .setLastName("Brown")
                .setBirthDay(new Date(2000-10-10))
                .setHomeTown("London")
                .build();

    }

    //Section 7. Item 43: Prefer method references to lambdas
    //Section 5. Item 26: Don’t use raw types
    @Test
    public void shouldCollectAll(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employee2);

        assertEquals(Lists.newArrayList("DAN", "CAROL"), employeeList.stream()
                .map(Employee::getFirstName)
                .map(String::toUpperCase)
                .collect(Collectors.toList()));
    }

}