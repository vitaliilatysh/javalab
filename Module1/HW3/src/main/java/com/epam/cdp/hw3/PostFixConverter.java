package com.epam.cdp.hw3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostFixConverter {
    private String infix;
    private Deque<Character> stack = new ArrayDeque<>();
    private List<String> postfix = new ArrayList<>();

    PostFixConverter(){

    }

    PostFixConverter(String expression) {
        infix = expression;
        convertExpression();
    }

    private void convertExpression() {
        StringBuilder temp = new StringBuilder();

        for (int index = 0; index != infix.length(); ++index) {
            if (Character.isDigit(infix.charAt(index))) {
                temp.append(infix.charAt(index));

                while ((index + 1) != infix.length() && (Character.isDigit(infix.charAt(index + 1))
                        || infix.charAt(index + 1) == '.')) {
                    temp.append(infix.charAt(++index));
                }

                postfix.add(temp.toString());
                temp.delete(0, temp.length());
            } else {
                inputToStack(infix.charAt(index));
            }
        }
        clearStack();
    }

    private void inputToStack(char input) {
        if (stack.isEmpty() || input == '(') {
            stack.addLast(input);
        } else {
            if (input == ')') {
                while (!stack.getLast().equals('(')) {
                    postfix.add(stack.removeLast().toString());
                }
                stack.removeLast();
            } else {
                if (stack.getLast().equals('(')) {
                    stack.addLast(input);
                } else {
                    while (!stack.isEmpty() && !stack.getLast().equals('(') &&
                            getPrecedence(input) <= getPrecedence(stack.getLast())) {
                        postfix.add(stack.removeLast().toString());
                    }
                    stack.addLast(input);
                }
            }
        }
    }

    private int getPrecedence(char operator) {

        if (operator == '+' || operator == '-') {
            return 1;
        }

        if (operator == '*' || operator == '/') {
            return 2;
        }

        return 0;
    }


    private void clearStack() {
        while (!stack.isEmpty()) {
            postfix.add(stack.removeLast().toString());
        }
    }


    List<String> getPostfixAsList() {
        return postfix;
    }
}
