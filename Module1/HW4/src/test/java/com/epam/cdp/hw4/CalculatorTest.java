package com.epam.cdp.hw4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class CalculatorTest {

    private Calculator calculator = new Calculator();
    @Test
    public void testAddNumbersShouldReturn0IfInputEmpty(){
        assertEquals(calculator.addInt(""), 0);
    }

    @Test
    public void testAddNumbersShouldReturnNumberIfInputOneNumber(){
        assertEquals(calculator.addInt("1"), 1);
    }

    @Test
    public void testAddNumbersShouldReturnSumOfTwoNumbers(){
        assertEquals(calculator.addInt("1,2"), 3);
    }
}
