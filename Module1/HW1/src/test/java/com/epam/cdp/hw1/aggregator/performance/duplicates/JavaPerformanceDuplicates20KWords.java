package com.epam.cdp.hw1.aggregator.performance.duplicates;

import com.epam.cdp.hw1.aggregator.performance.JavaAggregatorPerformanceTest;
import org.junit.Test;

public class JavaPerformanceDuplicates20KWords extends JavaAggregatorPerformanceTest {

    static {
        WORDS_20K = generateRandomWords(20_000);
    }

    @Test
    public void java7Aggregator_Duplicates_20K_Words() {
        runDuplicatesWith(java7Aggregator, WORDS_20K);
    }

    @Test
    public void java7ParallelAggregator_Duplicates_20K_Words() {
        runDuplicatesWith(java7ParallelAggregator, WORDS_20K);
    }

    @Test
    public void java8Aggregator_Duplicates_20K_Words() {
        runDuplicatesWith(java8Aggregator, WORDS_20K);
    }

    @Test
    public void java8ParallelAggregator_Duplicates_20K_Words() {
        runDuplicatesWith(java8ParallelAggregator, WORDS_20K);
    }
}
