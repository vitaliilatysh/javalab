package com.epam.cdp.calculator

import spock.lang.Specification

class GroovyCalculatorTest extends Specification {

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
        "2&2"                    | "0"              //using any unknown operator will give 0
    }

    def "when given expression has a division by zero then expression is thrown"() {
        when:
        calculator.calculate("(5+5)/(5-5)")
        then:
        ArithmeticException thrown = thrown(ArithmeticException)
        thrown.message == "Cannot calculate the expression. Cause: division to 0."
    }

    def "when given expression is empty then exception is thrown"() {
        when:
        calculator.calculate("")
        then:
        thrown(IllegalArgumentException)
    }

}
