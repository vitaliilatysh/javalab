package com.epam.cdp.hw2.effectivejava;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class EmployeeTest {
    // Section 2. Item 2: Consider a builder when faced with many constructor
    //parameters

    @Test
    public void shouldCreateObjectWithFields(){
        Employee item = Employee.newBuilder()
                .setFirstName("Dan")
                .setLastName("Brown")
                .setBirtDay(new Date(2000-10-10))
                .setHomeTown("London")
                .build();

        assertNotNull(item);
        assertEquals(item.getFirstName(), "Dan");
        assertEquals(item.getLastName(), "Brown");
        assertEquals(item.getHomeTown(), "London");
        assertNotNull(item.getBirthDay());
    }
}