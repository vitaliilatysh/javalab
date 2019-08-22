package com.epam.cdp.hw3;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class CalculatorTemporaryFolderRuleTest {

    private PostFixConverter postFixConverter = new PostFixConverter();
    private PostFixCalculator postFixCalculator = new PostFixCalculator();
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    @Test
    public void testInTempFolder() throws IOException {
        File tempFile = temporaryFolder.newFile("file.txt");

        BigDecimal calculationResult = postFixCalculator.result(postFixConverter.convertExpression("1+1"));
        Files.write(Paths.get(tempFile.getAbsolutePath()), calculationResult.toString().getBytes());

        String result = Files.readAllLines(Paths.get(tempFile.getAbsolutePath())).get(0);

        assertEquals(calculationResult.toString(), result);
    }

}