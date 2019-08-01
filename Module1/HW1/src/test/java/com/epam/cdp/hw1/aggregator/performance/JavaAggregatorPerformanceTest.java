package com.epam.cdp.hw1.aggregator.performance;

import com.epam.cdp.hw1.aggregator.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JavaAggregatorPerformanceTest {

    private static List<Integer> INTEGERS_20K = new ArrayList<>();
    private static List<Integer> INTEGERS_100 = new ArrayList<>();
    private static List<String> WORDS_100;
    private static List<String> WORDS_20K;

    private Java7Aggregator java7Aggregator = new Java7Aggregator();
    private Java7ParallelAggregator java7ParallelAggregator = new Java7ParallelAggregator();
    private Java8Aggregator java8Aggregator = new Java8Aggregator();
    private Java8ParallelAggregator java8ParallelAggregator = new Java8ParallelAggregator();

    static {
        Random random = new Random();
        for (int i = 1; i <= 10000000; i++) {
            INTEGERS_20K.add(random.ints(1, (1000 + 1)).findFirst().getAsInt());
        }

        for (int i = 1; i <= 100; i++) {
            INTEGERS_100.add(random.ints(1, (1000 + 1)).findFirst().getAsInt());
        }

        WORDS_100 = generateRandomWords(100);
        WORDS_20K = generateRandomWords(20_000);
    }

    private static List<String> generateRandomWords(int numberOfWords) {
        List<String> randomStrings = new ArrayList<>(numberOfWords);
        Random random = new Random();
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[random.nextInt(1) + 3];
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(3));
            }
            randomStrings.add(new String(word));
        }
        return randomStrings;
    }

    private static void runSumWith(Aggregator aggregator, List<Integer> data) {
        aggregator.sum(data);
    }

    private static void runFrequentWordsWith(Aggregator aggregator, List<String> data) {
        aggregator.getMostFrequentWords(data, 3);
    }

    private static void runDuplicatesWith(Aggregator aggregator, List<String> data) {
        aggregator.getDuplicates(data, 3);
    }

    @Test
    public void java7Aggregator_sum100_integers() {
        runSumWith(java7Aggregator, INTEGERS_100);
    }

    @Test
    public void java7ParallelAggregator_sum100_integers() {
        runSumWith(java7ParallelAggregator, INTEGERS_100);
    }

    @Test
    public void java8Aggregator_sum100_integers() {
        runSumWith(java8Aggregator, INTEGERS_100);
    }

    @Test
    public void java8ParallelAggregator_sum100_integers() {
        runSumWith(java8ParallelAggregator, INTEGERS_100);
    }

    @Test
    public void java7Aggregator_sum20K_integers() {
        runSumWith(java7Aggregator, INTEGERS_20K);
    }

    @Test
    public void java7ParallelAggregator_sum20K_integers() {
        runSumWith(java7ParallelAggregator, INTEGERS_20K);
    }

    @Test
    public void java8Aggregator_sum20K_integers() {
        runSumWith(java8Aggregator, INTEGERS_20K);
    }

    @Test
    public void java8ParallelAggregator_sum20K_integers() {
        runSumWith(java8ParallelAggregator, INTEGERS_20K);
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