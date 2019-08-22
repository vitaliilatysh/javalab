package com.epam.cdp.hw3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Scanner;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(RunCalculator.class)
public class CalculatorMockConsoleFileTest {


    @Test
    public void testMockConsole() {

        PostFixConverter converter = spy(PostFixConverter.class);
        PostFixCalculator calculator = spy(PostFixCalculator.class);

        PowerMockito.mockStatic(System.class);
        Scanner scanner = PowerMockito.mock(Scanner.class);

        when(scanner.next()).thenReturn("1+1").thenReturn("2/2").thenReturn("exit");

        RunCalculator.consoleMode(converter, calculator, scanner);

    }

}