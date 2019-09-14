package com.epam.cdp.calculator

class PostFixConverter implements IPostFixConverter {

    private Deque<Character> stack = new ArrayDeque<>()
    private List<String> postfix = new ArrayList<>()

    @Override
    List<String> convertExpression(String infix) {
        def length = infix?.length()

        if (infix == null || infix.isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty. Please, enter the expression.")
        }
        StringBuilder temp = new StringBuilder()

        for (int index = 0; index != length; ++index) {
            if (Character.isDigit(infix.charAt(index))) {
                temp.append(infix.charAt(index))

                while ((index + 1) != length && (Character.isDigit(infix.charAt(index + 1))
                        || infix.charAt(index + 1).toString() == '.')) {
                    temp.append(infix.charAt(++index))
                }

                postfix.add(temp.toString())
                temp.delete(0, temp.length())
            } else {
                inputToStack(infix.charAt(index))
            }
        }
        clearStack()
        return postfix
    }

    private void inputToStack(char input) {
        if (stack.isEmpty() || input.toString() == '(') {
            stack.addLast(input)
        } else {
            if (input.toString() == ')') {
                while (stack.getLast() != '(') {
                    postfix.add(stack.removeLast().toString())
                }
                stack.removeLast()
            } else {
                if (stack.getLast() == '(') {
                    stack.addLast(input)
                } else {
                    while (!stack.isEmpty() && stack.getLast() != '(' &&
                            getPrecedence(input) <= getPrecedence(stack.getLast())) {
                        postfix.add(stack.removeLast().toString())
                    }
                    stack.addLast(input)
                }
            }
        }
    }

    private int getPrecedence(char operator) {
        String giveOperator = operator.toString()

        if (giveOperator == '+' || giveOperator == '-') {
            return 1
        }

        if (giveOperator == '*' || giveOperator == '/') {
            return 2
        }

    }


    private void clearStack() {
        while (!stack.isEmpty()) {
            postfix.add(stack.removeLast().toString())
        }
    }
}
