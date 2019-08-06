package com.epam.cdp.hw1.aggregator.performance.frequency;

import com.epam.cdp.hw1.aggregator.performance.JavaAggregatorPerformanceTest;
import org.junit.Test;

public class JavaPerformanceFrequency100Words extends JavaAggregatorPerformanceTest {

    static {
        WORDS_100 = generateRandomWords(100);
    }

    @Test
    public void java7Aggregator_MostFrequent_100Words() {
        runFrequentWordsWith(java7Aggregator, WORDS_100);
    }

    @Test
    public void java7ParallelAggregator_MostFrequent_100Words() {
        runFrequentWordsWith(java7ParallelAggregator, WORDS_100);
    }

    @Test
    public void java8Aggregator_MostFrequent_100Words() {
        runFrequentWordsWith(java8Aggregator, WORDS_100);
    }

    @Test
    public void java8ParallelAggregator_MostFrequent_100Words() {
        runFrequentWordsWith(java8ParallelAggregator, WORDS_100);
    }
}
