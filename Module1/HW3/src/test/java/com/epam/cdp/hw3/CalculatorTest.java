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
public class CalculatorTest {

    private String expression;
    private BigDecimal expectedResult;

    public CalculatorTest(String expression, BigDecimal expectedResult){
        this.expression = expression;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters(name = "{index}: expression({0}) = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"0", new BigDecimal(0)},
                {"2+2", new BigDecimal(4)},
                {"2-2", new BigDecimal(0)},
                {"2*3", new BigDecimal(6)},
                {"3/3", new BigDecimal(1)},
                {"5/2", new BigDecimal(2.5)},
                {"2+2+2", new BigDecimal(6)},
                {"2*5-2", new BigDecimal(8)},
                {"2+3*4", new BigDecimal(14)},
                {"2+3*4+2", new BigDecimal(16)},
                {"2+2-2-2*10", new BigDecimal(-18)},
                {"2+2-2-2*10/2", new BigDecimal(-8)},
                {"2*2/2*2", new BigDecimal(4)},
                {"2*2/2+1.5", new BigDecimal(3.5)},
                {"2+2-2-2*10-8.5/2", new BigDecimal(-22.25)},
                {"(4+4)", new BigDecimal(8)},
                {"(4+4)*2-10.05/(45+5)+3", new BigDecimal(18.799).setScale(3, BigDecimal.ROUND_HALF_UP)}

        });
    }

    @Test
    public void test(){
        PostFixConverter pc = new PostFixConverter(expression);
        PostFixCalculator calc = new PostFixCalculator(pc.getPostfixAsList());

        assertThat(calc.result(), is(expectedResult));
    }

}