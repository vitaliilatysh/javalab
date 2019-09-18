package com.epam.cdp.calculator

class GroovyCalculator{

    /**
     * Run in console
     *
     * @param args parameters
     */
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in)
        PostFixCalculator calculator = new PostFixCalculator()

        consoleMode(calculator, scanner)

    }

    /**
     * Console calculator mode
     *
     * @param scanner input
     */

    static void consoleMode(PostFixCalculator calculator, Scanner scanner) {
        String expression

        while (!(expression = scanner.next()).isEmpty()) {
            if ("exit" == expression) {
                System.exit(1)
                break
            }

            println(calculator.calculate(expression))
        }
    }
}