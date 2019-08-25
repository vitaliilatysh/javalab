package com.epam.cdp.hw3;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class ExecutionTestInfo extends TestWatcher {

    private static final String EOL =
            System.getProperty("line.separator");
    private static StringBuilder builder = new StringBuilder();
    private File file = new File("test_execution_info.txt");

    @Override
    protected void succeeded(Description description) {
        if (description != null) {
            builder.append(description);
        }
        builder.append(" OK");
        builder.append(EOL);
        try {
            Files.write(Paths.get(file.getAbsolutePath()), Collections.singleton(builder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void failed(Throwable e, Description description) {
        if (description != null) {
            builder.append(description);
        }
        if (e != null) {
            builder.append(' ');
            builder.append(e);
        }
        builder.append(" FAIL");
        builder.append(EOL);

        try {
            Files.write(Paths.get(file.getAbsolutePath()), Collections.singleton(builder));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
