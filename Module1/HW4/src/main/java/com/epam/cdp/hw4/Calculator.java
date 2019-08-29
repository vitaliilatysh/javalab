package com.epam.cdp.hw4;

public class Calculator {

    int addInt(String numbers) {
        int sum = 0;

        if (numbers.isEmpty()) {
            return sum;
        }

        String[] numberLiterals;
        String delimiters = ",\n";

        if (numbers.startsWith("//")) {
            int endIndexOptionalLine = numbers.indexOf("\n");
            if (endIndexOptionalLine != -1) {
                String defaultDelimiter = numbers.substring(endIndexOptionalLine - 1, endIndexOptionalLine);
                delimiters = delimiters.concat(defaultDelimiter);
                numbers = numbers.substring(endIndexOptionalLine + 1);
            }
        }

        numberLiterals = numbers.split("[" + delimiters + "]");

        for (String number : numberLiterals) {
            sum += Integer.parseInt(number);
        }

        return sum;
    }
}
