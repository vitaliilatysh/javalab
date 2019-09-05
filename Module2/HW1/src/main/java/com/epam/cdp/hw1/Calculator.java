package com.epam.cdp.hw1;

public class Calculator {
    public int add(int a, int b) {
        System.out.println("This is add method for 2 params");
        return a + b;
    }

    public int add(int a, int b, int c) {
        System.out.println("This is add method for 3 params");
        return a + b + c;
    }

    public int add(int a, int b, int c, int d) {
        System.out.println("This is add method for 4 params");
        return a + b + c + d;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public double div(int a, int b) {
        return (double) a / b;
    }

    public int mult(int a, int b) {
        return a * b;
    }

    public double sqrt(int a) {
        return Math.sqrt(a);
    }
}
