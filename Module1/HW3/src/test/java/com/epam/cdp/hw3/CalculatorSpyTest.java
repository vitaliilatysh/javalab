package com.epam.cdp.hw3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


@RunWith(JUnit4.class)
public class CalculatorSpyTest {

    private PostFixCalculator calculator = spy(PostFixCalculator.class);

    @Test
    public void testSpyCallRealMethod() {
        when(calculator.calculate("1+1")).thenCallRealMethod();
        assertEquals(calculator.calculate("1+1").toString(), "2");
    }

    @Test
    public void testSpyCallMock() {
        when(calculator.calculate("1+3")).thenReturn(new BigDecimal(4));
        assertEquals(calculator.calculate("1+3").toString(), "4");
    }

}