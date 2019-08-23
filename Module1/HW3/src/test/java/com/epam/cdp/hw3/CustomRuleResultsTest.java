package com.epam.cdp.hw3;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

//TODO: need to find out how to implement TestRule properly
public class CustomRuleResultsTest {

    @Rule
    public TestResults testResults = new TestResults();
    private PostFixConverter postFixConverter = new PostFixConverter();
    List<String> postFixExpression = postFixConverter.convertExpression("1+1");
    private PostFixCalculator postFixCalculator = new PostFixCalculator();

    @Test
    public void writeTestResultToFile() {
        postFixCalculator.result(postFixExpression).toString();
    }
}
