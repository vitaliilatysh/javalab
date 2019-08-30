package com.epam.cdp.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    int addInt(String numbers) {
        int sum = 0;

        if (numbers.isEmpty()) {
            return sum;
        }

        String[] numberLiterals = null;
        String delimiters = ",\n";
        String negativeRegex = "-\\d+";

        checkNegativesInput(numbers, negativeRegex);

        if (numbers.startsWith("//")) {
            int endIndexOptionalLine = numbers.indexOf("\n");
            int startIndexOptionalLine = numbers.indexOf("/");
            if (endIndexOptionalLine != -1) {
                String defaultDelimiter = numbers.substring(startIndexOptionalLine + 2, endIndexOptionalLine);
                numbers = numbers.substring(endIndexOptionalLine + 1);
                numberLiterals = numbers.split(buildDefaultDelimiter(defaultDelimiter));
            }
        } else {
            numberLiterals = numbers.split("[" + delimiters + "]");
        }

        return getSum(numberLiterals);
    }

    private int getSum(String[] numberLiterals) {
        int sum = 0;
        for (String number : numberLiterals) {
            if (Integer.parseInt(number) > 1000) {
                continue;
            }
            sum += Integer.parseInt(number);
        }
        return sum;
    }

    private String buildDefaultDelimiter(String currentDelimiter) {
        StringBuilder newString = new StringBuilder();
        String[] array = new String[currentDelimiter.length()];
        for (int i = 0; i < currentDelimiter.length(); i++) {
            array[i] = String.valueOf(currentDelimiter.charAt(i));
            newString.append("\\").append(array[i]);
        }
        currentDelimiter = newString.toString();
        return currentDelimiter;
    }

    private void checkNegativesInput(String numbers, String negativeRegex) {
        Pattern pattern = Pattern.compile(negativeRegex);
        Matcher matcher = pattern.matcher(numbers);
        List<String> negatives = new ArrayList<>();

        while (matcher.find()) {
            negatives.add(matcher.group());
        }

        if (negatives.size() == 0) {
            return;
        }

        if (negatives.size() == 1) {
            throw new IllegalArgumentException("negatives not allowed");
        }

        throw new IllegalArgumentException(String.join(",", negatives));
    }
}
