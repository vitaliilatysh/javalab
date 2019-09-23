package com.epam.cdp.hw3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;

public class NumberSorter {

    /**
     * Run app
     *
     * @param args params
     * @throws IOException if file reading problem exist
     */
    public static void main(String[] args) throws IOException {
        File input = new File("./src/main/resources/numbers.txt");
        File output = new File("./src/main/resources/java_sorted.txt");
        Path inputPath = Paths.get(input.getAbsolutePath());
        Path outputPath = Paths.get(output.getAbsolutePath());

        List<String> numbers = Files.readAllLines(inputPath);

        long startSorting = System.currentTimeMillis();
        numbers.sort(Comparator.comparing(Integer::valueOf));
        long finishSorting = System.currentTimeMillis();

        Files.write(outputPath, numbers, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        System.out.println("Total time to sort in seconds: " + (finishSorting - startSorting)/1000);
    }
}
