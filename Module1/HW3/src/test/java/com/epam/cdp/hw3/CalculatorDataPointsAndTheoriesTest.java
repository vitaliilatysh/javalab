package com.epam.cdp.hw3;

import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class CalculatorDataPointsAndTheoriesTest {

    @DataPoint
    public static final String add = "+";
    @DataPoint
    public static final String extract = "-";

    private PostFixConverter postFixConverter = new PostFixConverter();
    private PostFixCalculator postFixCalculator = new PostFixCalculator();

    @DataPoints("floats")
    public static String[] floats() {
        return new String[]{"0.00012", "999.4302", "0.234", "0.01"};
    }

    @DataPoints("integers")
    public static String[] integers() {
        return new String[]{"1000", "2", "55"};
    }

    @Theory
    public void add(@FromDataPoints("floats") String number1,
                    @FromDataPoints("integers") String number2) {
        String expression = number1 + add + number2;

        assertEquals(postFixCalculator.result(postFixConverter.convertExpression(expression)),
                new BigDecimal(number1).add(new BigDecimal(number2)).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros());
    }

    @Theory
    public void extract(@FromDataPoints("floats") String number1,
                        @FromDataPoints("integers") String number2) {
        String expression = number1 + extract + number2;

        assertEquals(postFixCalculator.result(postFixConverter.convertExpression(expression)),
                (new BigDecimal(number1).subtract(new BigDecimal(number2))).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros());
    }

}