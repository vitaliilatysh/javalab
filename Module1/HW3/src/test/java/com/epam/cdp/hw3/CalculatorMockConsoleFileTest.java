package com.epam.cdp.hw3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(RunCalculator.class)
public class CalculatorMockConsoleFileTest {

    @Test
    public void testConsoleMode() {
        PowerMockito.mockStatic(System.class);
        Scanner scanner = PowerMockito.mock(Scanner.class);
        PostFixCalculator calculator = spy(PostFixCalculator.class);

        when(scanner.next()).thenReturn("1+1").thenReturn("2/4").thenReturn("exit");

        RunCalculator.consoleMode(calculator, scanner);

        verify(calculator, times(2)).calculate(anyString());
    }

    @Test
    public void testFileMode() throws IOException {
        PostFixCalculator calculator = spy(PostFixCalculator.class);
        FileUtils fileUtils = mock(FileUtils.class);

        String[] args = new String[]{"file1", "file2"};
        List<String> expressions = new ArrayList<>();
        expressions.add("1+1");
        expressions.add("2/4");

        when(fileUtils.readFromFile(any(File.class))).thenReturn(expressions);
        when(calculator.calculate("1+1")).thenReturn(new BigDecimal(2));
        when(calculator.calculate("2/4")).thenReturn(new BigDecimal(0.5));
        doNothing().when(fileUtils).writeToFile(any(File.class), anyList());

        RunCalculator.fileMode(fileUtils, calculator, args);

        verify(calculator, times(2)).calculate(anyString());
        verify(fileUtils, times(1)).readFromFile(any(File.class));
        verify(fileUtils, times(1)).writeToFile(any(File.class), anyList());

    }

}