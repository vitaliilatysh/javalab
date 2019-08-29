package com.epam.cdp.hw4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Rule
    public ExpectedException exportException = ExpectedException.none();

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

    @Test
    public void testAddNumbersShouldReturnSumAnyAmountOfNumbers(){
        assertEquals(calculator.addInt("1,2,3,4,5,6"), 21);
    }

    @Test
    public void testAddNumbersShouldReturnSumAny2NumbersDiffDelimiters(){
        assertEquals(calculator.addInt("1\n2,3"), 6);
    }

    @Test
    public void testAddNumbersShouldReturnSumAny2NumbersSemicolonDelimiter(){
        assertEquals(calculator.addInt("//;\n1;2"), 3);
    }

    @Test
    public void testAddNumbersShouldReturnSumAny2NumbersDotDelimiter(){
        assertEquals(calculator.addInt("//.\n1.2.4"), 7);
    }

    @Test
    public void testAddNumbersShouldThrowExceptionIfNegativeNumberInput(){
        exportException.expect(IllegalArgumentException.class);
        exportException.expectMessage("negatives not allowed");
        calculator.addInt("//.\n1.-7.4");
    }

    @Test
    public void testAddNumbersShouldThrowExceptionIfNegativeNumbersInputShowAllNegatives(){
        exportException.expect(IllegalArgumentException.class);
        exportException.expectMessage("-7,-5");
        calculator.addInt("//.\n1.-7.-5");
    }

    @Test
    public void testAddNumbersShouldIgnoreNumbersMore1000(){
        assertEquals(calculator.addInt("//.\n1001.1.2"), 3);
    }

}
