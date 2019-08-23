package com.epam.cdp.hw3;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.util.Arrays;

//TODO:
public class TestResults implements MethodRule {

//    @Override
//    public Statement apply(Statement base, Description description) {
//        File file = new File("results.txt");
//        try {
//            base.evaluate();
//
//            Files.write(Paths.get(file.getAbsolutePath()), new ArrayList<>());
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        return base;
//    }

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        try {
            base.evaluate();

            ((PostFixCalculator) target).result(Arrays.asList("1", "1", "+"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
