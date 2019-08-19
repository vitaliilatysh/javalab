package com.epam.cdp.hw3;

import java.util.Scanner;

public class Main {
    /**
     * Run in console
     * @param args parameters
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String expression;

        while (!(expression = scanner.next()).isEmpty()) {
            if(expression.equals("exit")){
                System.exit(1);
            }

            PostFixConverter pc = new PostFixConverter(expression);
            PostFixCalculator calc = new PostFixCalculator(pc.getPostfixAsList());
            System.out.println(calc.result());
        }

    }
}
