package com.epam.cdp.hw3;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class PostFixCalculator implements IPostFixCalculator {
    private Deque<Double> stack = new ArrayDeque<>();

    @Override
    public BigDecimal result(List<String> expression) {
        for (int index = 0; index != expression.size(); ++index) {
            if (Character.isDigit(expression.get(index).charAt(0))) {
                stack.addLast(Double.parseDouble(expression.get(index)));
            } else {
                double tempResult = 0;
                double temp;

                switch (expression.get(index)) {
                    case "+":
                        temp = stack.removeLast();
                        tempResult = stack.removeLast() + temp;
                        break;

                    case "-":
                        temp = stack.removeLast();
                        tempResult = stack.removeLast() - temp;
                        break;

                    case "*":
                        temp = stack.removeLast();
                        tempResult = stack.removeLast() * temp;
                        break;

                    case "/":
                        temp = stack.removeLast();
                        tempResult = stack.removeLast() / temp;
                        break;
                    default:
                        break;
                }
                stack.addLast(tempResult);
            }
        }
        return new BigDecimal(stack.removeLast()).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
    }
}
