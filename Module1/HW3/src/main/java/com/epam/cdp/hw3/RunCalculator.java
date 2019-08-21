package com.epam.cdp.hw3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunCalculator {
    /**
     * Run in console
     * @throws IOException if some file write/read issues to file
     * @param args parameters
     */
    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            File inputFile = new File(args[0]);
            File outputFile = new File(args[1]);

            List<String> expressions = Files.readAllLines(Paths.get(inputFile.getAbsolutePath()));

            List<String> resultLines = new ArrayList<>();

            for (String expression : expressions) {
                PostFixConverter pc = new PostFixConverter(expression);
                PostFixCalculator calc = new PostFixCalculator(pc.getPostfixAsList());
                resultLines.add(calc.result().toString());
            }
            Files.write(Paths.get(outputFile.getAbsolutePath()), resultLines);

        } else {
            Scanner scanner = new Scanner(System.in);

            String expression;

            while (!(expression = scanner.next()).isEmpty()) {
                if (expression.equals("exit")) {
                    System.exit(1);
                }

                PostFixConverter pc = new PostFixConverter(expression);
                PostFixCalculator calc = new PostFixCalculator(pc.getPostfixAsList());
                System.out.println(calc.result());
            }
        }
    }
}
