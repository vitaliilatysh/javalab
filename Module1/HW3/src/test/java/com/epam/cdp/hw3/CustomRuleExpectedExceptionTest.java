package com.epam.cdp.hw3;

import com.epam.cdp.hw3.rules.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;


public class CustomRuleExpectedExceptionTest {


    @Rule
    public ExpectedException executionTestInfo = new ExpectedException();

    private PostFixConverter postFixConverter = new PostFixConverter();
    private PostFixCalculator postFixCalculator = new PostFixCalculator();

    @Test
    public void testExceptionIfNoExpression() {
        executionTestInfo.expectedException(IllegalArgumentException.class);
        executionTestInfo.expectMessage("Expression cannot be null or empty. Please, enter the expression.");

        List<String> postFixExpression = postFixConverter.convertExpression("");
        postFixCalculator.result(postFixExpression);

    }

    @Test
    public void testExceptionIfZeroDivision() {
        executionTestInfo.expectedException(ArithmeticException.class);
        executionTestInfo.expectMessage("Cannot calculate the expression. Cause: division to 0.");

        List<String> postFixExpression = postFixConverter.convertExpression("(5+5)/(5-5)");
        postFixCalculator.result(postFixExpression);

    }

}
