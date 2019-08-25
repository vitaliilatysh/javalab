package com.epam.cdp.hw3;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class CustomRuleResultsTest {

    @Rule
    public ExecutionTestInfo executionTestInfo = new ExecutionTestInfo();
    private PostFixConverter postFixConverter = new PostFixConverter();
    private PostFixCalculator postFixCalculator = new PostFixCalculator();

    @Test
    public void shouldReturn2If1plus1() {
        List<String> postFixExpression = postFixConverter.convertExpression("1+1");
        assertEquals(postFixCalculator.result(postFixExpression).toString(), "2");
    }

    @Test
    public void shouldReturn1If1Divide1() {
        List<String> postFixExpression = postFixConverter.convertExpression("1/1");
        assertEquals(postFixCalculator.result(postFixExpression).toString(), "1");
    }
}
