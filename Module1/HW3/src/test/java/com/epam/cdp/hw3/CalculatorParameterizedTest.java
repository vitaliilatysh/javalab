package com.epam.cdp.hw3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

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
                {"(4+4)*2-10.05/(45+5)+3", "18.799"}

        });
    }

    @Test
    public void test(){
        PostFixConverter pc = new PostFixConverter(expression);
        PostFixCalculator calc = new PostFixCalculator(pc.getPostfixAsList());

        assertThat(calc.result().toString(), is(expectedResult));
    }

}