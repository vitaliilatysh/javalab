package com.epam.cdp.hw3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.Scanner;

import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(RunCalculator.class)
public class CalculatorMockConsoleFileTest {


    @Test
    public void testMockConsole() throws IOException {

        PowerMockito.mockStatic(System.class);
        Scanner scanner = PowerMockito.mock(Scanner.class);

        when(scanner.next()).thenReturn("1+1");

        RunCalculator.main(new String[0]);

    }

}