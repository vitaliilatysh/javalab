package com.epam.cdp.hw3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

    List<String> readFromFile(File inputFile) throws IOException {
        return Files.readAllLines(Paths.get(inputFile.getAbsolutePath()));
    }

    void writeToFile(File outputFile, List<String> resultLines) throws IOException {
        Files.write(Paths.get(outputFile.getAbsolutePath()), resultLines);
    }
}
