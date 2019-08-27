package com.epam.cdp.hw3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(JUnit4.class)
public class CalculatorSpyTest {

    @InjectMocks
    private PostFixCalculator calculator;

    @Spy
    private PostFixConverter converter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSpyCallRealMethod() {
        when(converter.convertExpression("1+1")).thenCallRealMethod();
        assertEquals(calculator.calculate("1+1").toString(), "2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSpyCallMock() {
        when(converter.convertExpression("1+1")).thenThrow(IllegalArgumentException.class);
        calculator.calculate("1+1");
    }

}