package com.epam.cdp.hw3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunCalculator {
    private static Scanner scanner = new Scanner(System.in);
    private static PostFixConverter converter = new PostFixConverter();
    private static PostFixCalculator calculator = new PostFixCalculator();

    /**
     * Run in console
     *
     * @param args parameters
     * @throws IOException if some file write/read issues to file
     */
    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            fileMode(converter, calculator, args);
        } else {
            consoleMode(converter, calculator, scanner);
        }
    }

    /**
     * @param pc   postfix converter
     * @param calc calculator
     * @param args file names: input file and output
     * @throws IOException if some file write/read issues to file
     */
    public static void fileMode(PostFixConverter pc, PostFixCalculator calc, String[] args) throws IOException {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);

        List<String> expressions = Files.readAllLines(Paths.get(inputFile.getAbsolutePath()));

        List<String> resultLines = new ArrayList<>();

        for (String expression : expressions) {
            List<String> postfixExpression = pc.convertExpression(expression);
            resultLines.add(calc.result(postfixExpression).toString());
        }
        Files.write(Paths.get(outputFile.getAbsolutePath()), resultLines);
    }

    /**
     * Console calculator mode
     * @param pc postfix converter
     * @param calc calculator itself
     * @param scanner input
     */
    public static void consoleMode(PostFixConverter pc, PostFixCalculator calc, Scanner scanner) {
        String expression;

        while (!(expression = scanner.next()).isEmpty()) {
            if (expression.equals("exit")) {
                System.exit(1);
                break;
            }

            List<String> postfixExpression = pc.convertExpression(expression);
            System.out.println(calc.result(postfixExpression));
        }
    }
}
