package com.epam.cdp.hw1;

public class Calculator {
    public int add(int number1, int number2) {
        System.out.println("This is add method");
        return number1 + number2;
    }

    public int add(int number1, int number2, int number3) {
        System.out.println("This is add method for 3 params");
        return number1 + number2 + number3;
    }

    public int add(int number1, int number2, int number3, int number4) {
        System.out.println("This is add method for 4 params");
        return number1 + number2 + number3 + number4;
    }

    public int sub(int number1, int number2) {
        return number1 - number2;
    }

    public double div(int number1, int number2) {
        return (double) number1 / number2;
    }

    public int mult(int number1, int number2) {
        return number1 * number2;
    }

    public double sqrt(int number) {
        return Math.sqrt(number);
    }
}
