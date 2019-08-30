package com.epam.cdp.hw4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CalculatorExceptionTest {

    @Rule
    public ExpectedException exportException = ExpectedException.none();

    private Calculator calculator = new Calculator();

    @Test
    public void testAddNumbersShouldThrowExceptionIfNegativeNumberInput() {
        exportException.expect(IllegalArgumentException.class);
        exportException.expectMessage("negatives not allowed");
        calculator.addInt("//.\n1.-7.4");
    }

    @Test
    public void testAddNumbersShouldThrowExceptionIfNegativeNumbersInputShowAllNegatives() {
        exportException.expect(IllegalArgumentException.class);
        exportException.expectMessage("-7,-5");
        calculator.addInt("//.\n1.-7.-5");
    }

}
