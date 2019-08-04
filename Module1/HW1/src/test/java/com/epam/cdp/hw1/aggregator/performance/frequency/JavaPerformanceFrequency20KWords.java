package com.epam.cdp.hw1.aggregator.performance.frequency;

import com.epam.cdp.hw1.aggregator.performance.JavaAggregatorPerformanceTest;
import org.junit.Test;

import java.util.Random;

public class JavaPerformanceFrequency20KWords extends JavaAggregatorPerformanceTest {

    static {
        WORDS_20K = generateRandomWords(20_000);
    }

    @Test
    public void java7Aggregator_MostFrequent_20K_Words() {
        runFrequentWordsWith(java7Aggregator, WORDS_20K);
    }

    @Test
    public void java7ParallelAggregator_MostFrequent_20K_Words() {
        runFrequentWordsWith(java7ParallelAggregator, WORDS_20K);
    }

    @Test
    public void java8Aggregator_MostFrequent_20K_Words() {
        runFrequentWordsWith(java8Aggregator, WORDS_20K);
    }

    @Test
    public void java8ParallelAggregator_MostFrequent_20K_Words() {
        runFrequentWordsWith(java8ParallelAggregator, WORDS_20K);
    }
}
