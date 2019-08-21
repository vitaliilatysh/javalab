//package com.epam.cdp.hw3;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//
//@RunWith(JUnit4.class)
//public class CalculatorCustomRuleExceptionTest {
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @Test
//    public void shouldThrowExceptionIfDivisionBy0() {
//        thrown.expect(IllegalArgumentException.class);
//        thrown.expectMessage("Division by zero. Please, check the expression.");
//
//        PostFixConverter pc = new PostFixConverter("4/0");
//        PostFixCalculator calc = new PostFixCalculator(pc.getPostfixAsList());
//        calc.result();
//    }
//}
