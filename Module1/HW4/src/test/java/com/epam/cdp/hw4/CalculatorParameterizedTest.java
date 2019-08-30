package com.epam.cdp.hw4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class CalculatorParameterizedTest {

    private String expression;
    private int expectedResult;

    public CalculatorParameterizedTest(String expression, int expectedResult) {
        this.expression = expression;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters(name = "{index}: addInt({0}) = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", 0},
                {"1", 1},
                {"1,2", 3},
                {"1,2,3,4,5,6", 21},
                {"1\n2,3", 6},
                {"//;\n1;2", 3},
                {"//.\n1.2.4", 7},
                {"//.\n1001.1.2", 3},
                {"//.\n1000.1.2", 1003},
                {"//***\n1***2***3", 6},
                {"//...\n1...2...3", 6},
                {"//.*(\n1.2*3(4", 10},
                {"//...*((\n1...2*3((4", 10},
        });
    }

    @Test
    public void test() {
        Calculator calc = new Calculator();

        assertThat(calc.addInt(expression), is(expectedResult));
    }

}
