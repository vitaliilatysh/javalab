package com.epam.cdp.hw3;

import com.epam.cdp.hw3.rules.ExecutionTestInfo;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CustomRuleExecutionInfoTest {

    @Rule
    public ExecutionTestInfo executionTestInfo = new ExecutionTestInfo();

    private PostFixCalculator postFixCalculator = new PostFixCalculator();

    @Test
    public void shouldReturn2If1plus1() {
        assertEquals(postFixCalculator.calculate("1+1").toString(), "2");
    }

    @Test
    public void shouldReturn1If1Divide1() {
        assertEquals(postFixCalculator.calculate("1/1").toString(), "1");
    }
}
