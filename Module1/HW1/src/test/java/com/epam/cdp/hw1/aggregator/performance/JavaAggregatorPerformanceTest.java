package com.epam.cdp.hw1.aggregator.performance;

import com.epam.cdp.hw1.aggregator.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class JavaAggregatorPerformanceTest {

    protected static List<Integer> INTEGERS_20K = new ArrayList<>();
    protected static List<Integer> INTEGERS_100 = new ArrayList<>();
    protected static List<String> WORDS_100;
    protected static List<String> WORDS_20K;

    protected static Java7Aggregator java7Aggregator = new Java7Aggregator();
    protected static Java7ParallelAggregator java7ParallelAggregator = new Java7ParallelAggregator();
    protected static Java8Aggregator java8Aggregator = new Java8Aggregator();
    protected static Java8ParallelAggregator java8ParallelAggregator = new Java8ParallelAggregator();

    protected static List<String> generateRandomWords(int numberOfWords) {
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

    protected static void runSumWith(Aggregator aggregator, List<Integer> data) {
        aggregator.sum(data);
    }

    protected static void runFrequentWordsWith(Aggregator aggregator, List<String> data) {
        aggregator.getMostFrequentWords(data, 3);
    }

    protected static void runDuplicatesWith(Aggregator aggregator, List<String> data) {
        aggregator.getDuplicates(data, 3);
    }
}