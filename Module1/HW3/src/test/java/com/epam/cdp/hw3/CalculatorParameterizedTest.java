package com.epam.cdp.hw3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class CalculatorParameterizedTest {

    private String expression, expectedResult;

    public CalculatorParameterizedTest(String expression, String expectedResult){
        this.expression = expression;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters(name = "{index}: expression({0}) = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"0", "0"},
                {"2+2", "4"},
                {"2-2", "0"},
                {"2*3", "6"},
                {"3/3", "1"},
                {"5/2", "2.5"},
                {"2+2+2", "6"},
                {"2.2+100.5", "102.7"},
                {"2*5-2", "8"},
                {"2+3*4", "14"},
                {"2+3*4+2", "16"},
                {"2+2-2-2*10", "-18"},
                {"2+2-2-2*10/2", "-8"},
                {"2*2/2*2", "4"},
                {"2*2/2+1.5", "3.5"},
                {"2+2-2-2*10-8.5/2", "-22.25"},
                {"(4+4)", "8"},
                {"(4+4)*2-10.05/(45+5)+3", "18.799"},
                {"((3*3.5)/100)*0.124-45", "-44.98698"}
        });
    }

    @Test
    public void test() {
        PostFixConverter pc = new PostFixConverter();
        PostFixCalculator calc = new PostFixCalculator();
        List<String> postFixExpression = pc.convertExpression(expression);

        assertThat(calc.result(postFixExpression).toString(), is(expectedResult));
    }

}