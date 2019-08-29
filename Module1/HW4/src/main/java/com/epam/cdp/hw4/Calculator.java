package com.epam.cdp.hw4;

public class Calculator {

    int addInt(String numbers){
        int sum = 0;

        if(numbers.isEmpty()){
            return sum;
        }

        String[] numberLiterals = numbers.split(",");

        for(String number: numberLiterals){
            sum+=Integer.parseInt(number);
        }

        return sum;
    }
}
