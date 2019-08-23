package com.epam.cdp.hw3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(RunCalculator.class)
public class CalculatorMockConsoleFileTest {

    private PostFixConverter converter = spy(PostFixConverter.class);
    private PostFixCalculator calculator = spy(PostFixCalculator.class);

    @Test
    public void testConsoleMode() {
        PowerMockito.mockStatic(System.class);
        Scanner scanner = PowerMockito.mock(Scanner.class);

        when(scanner.next()).thenReturn("1+1").thenReturn("2/4").thenReturn("exit");

        RunCalculator.consoleMode(converter, calculator, scanner);

        verify(converter, times(2)).convertExpression(anyString());
        verify(calculator, times(2)).result(anyList());
    }

    //TODO: need to find out how mock/spy fileMode method
    @Test
    public void testFileMode() throws IOException {

        PowerMockito.mockStatic(System.class);
        Scanner scanner = PowerMockito.mock(Scanner.class);
        PowerMockito.mock(Paths.class);
        PowerMockito.mock(Files.class);
        File file = mock(File.class);


        String[] args = new String[]{"file1", "file2"};
        List<String> expressions = new ArrayList<>();
        expressions.add("1+1");
        expressions.add("2/4");

//        when(file.getAbsolutePath()).thenReturn(anyString());
//        when(Paths.get(anyString())).thenReturn(any(Path.class));
        when(Files.readAllLines(Paths.get(anyString()))).thenReturn(expressions);

        RunCalculator.fileMode(converter, calculator, args);

    }

}