package com.epam.cdp.hw3.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ExpectedException implements TestRule {

    private String message;
    private Class<? extends Exception> exception;

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    base.evaluate();
                } catch (Exception ex) {
                    assertEquals(exception, ex.getClass());
                    assertEquals(message, ex.getMessage());
                    return;
                }
                fail("Expected exception: " + exception);
            }
        };
    }

    public void expectMessage(String expectedMessage) {
        this.message = expectedMessage;
    }

    public void expectedException(Class<? extends Exception> expectedException) {
        this.exception = expectedException;
    }
}
