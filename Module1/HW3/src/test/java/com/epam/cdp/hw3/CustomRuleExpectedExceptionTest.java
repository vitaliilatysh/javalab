package com.epam.cdp.hw3;

import com.epam.cdp.hw3.rules.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

public class CustomRuleExpectedExceptionTest {


    @Rule
    public ExpectedException executionTestInfo = new ExpectedException();

    private PostFixCalculator postFixCalculator = new PostFixCalculator();

    @Test
    public void testExceptionIfNoExpression() {
        executionTestInfo.expectedException(IllegalArgumentException.class);
        executionTestInfo.expectMessage("Expression cannot be null or empty. Please, enter the expression.");

        postFixCalculator.calculate("");

    }

    @Test
    public void testExceptionIfZeroDivision() {
        executionTestInfo.expectedException(ArithmeticException.class);
        executionTestInfo.expectMessage("Cannot calculate the expression. Cause: division to 0.");

        postFixCalculator.calculate("(5+5)/(5-5)");

    }

}
