package com.epam.cdp.hw1.aggregator.performance.duplicates;

import com.epam.cdp.hw1.aggregator.performance.JavaAggregatorPerformanceTest;
import org.junit.Test;

import java.util.Random;

public class JavaPerformanceDuplicates100Words extends JavaAggregatorPerformanceTest {

    static {
        WORDS_100 = generateRandomWords(100);
    }

    @Test
    public void java7Aggregator_Duplicates_100Words() {
        runDuplicatesWith(java7Aggregator, WORDS_100);
    }

    @Test
    public void java7ParallelAggregator_Duplicates_100Words() {
        runDuplicatesWith(java7ParallelAggregator, WORDS_100);
    }

    @Test
    public void java8Aggregator_Duplicates_100Words() {
        runDuplicatesWith(java8Aggregator, WORDS_100);
    }

    @Test
    public void java8ParallelAggregator_Duplicates_100Words() {
        runDuplicatesWith(java8ParallelAggregator, WORDS_100);
    }
}
