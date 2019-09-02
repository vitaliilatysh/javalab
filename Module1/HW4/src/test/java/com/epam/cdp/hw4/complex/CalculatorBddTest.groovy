package com.epam.cdp.hw4.complex

import spock.lang.Specification

class CalculatorBddTest extends Specification {

    PostFixCalculator calculator = new PostFixCalculator()

    def 'should calculate given expression'() {
        expect:
        calculator.calculate(expression).toString() == expected

        where:
        expression               | expected
        "0"                      | "0"
        "2+2"                    | "4"
        "2-2"                    | "0"
        "2*3"                    | "6"
        "3/3"                    | "1"
        "5/2"                    | "2.5"
        "2+2+2"                  | "6"
        "2.2+100.5"              | "102.7"
        "2*5-2"                  | "8"
        "2+3*4"                  | "14"
        "2+3*4+2"                | "16"
        "2+2-2-2*10"             | "-18"
        "2+2-2-2*10/2"           | "-8"
        "2*2/2*2"                | "4"
        "2*2/2+1.5"              | "3.5"
        "2+2-2-2*10-8.5/2"       | "-22.25"
        "(4+4)"                  | "8"
        "(4+4)*2-10.05/(45+5)+3" | "18.799"
        "((3*3.5)/100)*0.124-45" | "-44.98698"
    }

    def "when given expression has a division by zero then expression is thrown"() {
        when:
        calculator.calculate("(5+5)/(5-5)")
        then:
        thrown(ArithmeticException)
    }

    def "when given expression is empty then exception is thrown"() {
        when:
        calculator.calculate("")
        then:
        thrown(IllegalArgumentException)
    }

    def "when files are given as parameters then running file mode"() {

        given:
        PostFixCalculator calculator = Spy()
        FileUtils fileUtils = Mock()

        String[] args = ["file1", "file2"] as String[]
        List<String> expressions = new ArrayList<>()
        expressions.add("1+1")
        expressions.add("2/4")

        fileUtils.readFromFile(_ as File) >> { expressions }
        calculator.calculate(expressions.get(0)) >> new BigDecimal(2)
        calculator.calculate(expressions.get(1)) >> new BigDecimal(2)

        when:
        RunCalculator.fileMode(fileUtils, calculator, args)

        then:
        2 * calculator.calculate(_ as String)
    }
}
