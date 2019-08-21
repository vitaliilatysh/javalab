package com.epam.cdp.hw3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(JUnit4.class)
public class CalculatorSpyTest {

    @Test
    public void testSpy() {
        PostFixConverter postFixConverter = Mockito.spy(PostFixConverter.class);

        List<String> postfix = new ArrayList<>();
        postfix.add("1");
        postfix.add("1");
        postfix.add("+");

        when(postFixConverter.getPostfixAsList()).thenReturn(postfix);

        PostFixCalculator calculator = new PostFixCalculator(postFixConverter.getPostfixAsList());

        assertEquals(calculator.result().toString(), "2");

    }

}