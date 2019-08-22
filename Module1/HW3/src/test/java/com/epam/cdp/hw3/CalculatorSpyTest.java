package com.epam.cdp.hw3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


@RunWith(JUnit4.class)
public class CalculatorSpyTest {

    private PostFixCalculator calculator = new PostFixCalculator();
    private PostFixConverter postFixConverter = spy(PostFixConverter.class);

    @Test
    public void testSpy() {

        List<String> postfix = new ArrayList<>();
        postfix.add("1");
        postfix.add("1");
        postfix.add("+");

        when(postFixConverter.convertExpression("1+1")).thenCallRealMethod();
        when(postFixConverter.convertExpression("10+10")).thenReturn(postfix);

        List<String> postFixExpression1 = postFixConverter.convertExpression("1+1");
        List<String> postFixExpression2 = postFixConverter.convertExpression("10+10");

        assertEquals(calculator.result(postFixExpression1).toString(), "2");
        assertEquals(calculator.result(postFixExpression2).toString(), "2");

    }

}