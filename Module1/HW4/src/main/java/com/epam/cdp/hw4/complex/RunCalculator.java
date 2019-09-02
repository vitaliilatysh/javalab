package com.epam.cdp.hw4.complex;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunCalculator {

    /**
     * Run in console
     *
     * @param args parameters
     * @throws IOException if some file write/read issues to file
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        PostFixCalculator calculator = new PostFixCalculator();
        FileUtils fileUtils = new FileUtils();

        if (args.length > 0) {
            fileMode(fileUtils, calculator, args);
            return;
        }
        consoleMode(calculator, scanner);

    }

    /**
     * Calculator file mode
     *
     * @param args file names: input file and output
     * @throws IOException if some file write/read issues to file
     */
    static void fileMode(FileUtils fileUtils, PostFixCalculator calculator, String[] args) throws IOException {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);

        List<String> resultLines = new ArrayList<>();

        List<String> inputExpressions = fileUtils.readFromFile(inputFile);
        for (String expression : inputExpressions) {
            resultLines.add(calculator.calculate(expression).toString());
        }

        fileUtils.writeToFile(outputFile, resultLines);
    }

    /**
     * Console calculator mode
     *
     * @param scanner input
     */
    static void consoleMode(PostFixCalculator calculator, Scanner scanner) {
        String expression;

        while ((expression = scanner.next()) != null) {
            if (expression.equals("exit")) {
                System.exit(1);
                break;
            }

            System.out.println(calculator.calculate(expression));
        }
    }
}
