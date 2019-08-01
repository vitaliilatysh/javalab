package com.epam.cdp.hw1.aggregator.performance;

import com.epam.cdp.hw1.aggregator.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JavaSumPerformanceTest {

    private static final List<Integer> INTEGERS_20_000 = new ArrayList<>();
    private static final List<Integer> INTEGERS_100 = new ArrayList<>();

    static {
        Random random = new Random();
        for (int i = 1; i <= 10000000; i++) {
            INTEGERS_20_000.add(random.ints(1, (1000 + 1)).findFirst().getAsInt());
        }
    }

    static {
        Random random = new Random();
        for (int i = 1; i <= 100; i++) {
            INTEGERS_100.add(random.ints(1, (1000 + 1)).findFirst().getAsInt());
        }
    }

    private static List<String> generateRandomWords(int numberOfWords)
    {
        List<String> randomStrings = new ArrayList<>(numberOfWords);
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3];
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings.add(new String(word));
        }
        return randomStrings;
    }

    static{
        generateRandomWords(100);
        generateRandomWords(20_000);
    }

    private Java7Aggregator java7Aggregator = new Java7Aggregator();
    private Java7ParallelAggregator java7ParallelAggregator = new Java7ParallelAggregator();
    private Java8Aggregator java8Aggregator = new Java8Aggregator();
    private Java8ParallelAggregator java8ParallelAggregator = new Java8ParallelAggregator();

    private static void runSumWith(Aggregator aggregator, List<Integer> data){
        aggregator.sum(data);
    }

    @Test
    public void sumJava7AggregatorWithSmallAmountOfNumbers() {
        runSumWith(java7Aggregator, INTEGERS_100);
    }

    @Test
    public void sumJava7AggregatorWithBigAmountOfNumbers() {
        runSumWith(java7Aggregator, INTEGERS_20_000);
    }

    @Test
    public void sumJava8AggregatorWithSmallAmountOfNumbers() {
        runSumWith(java8Aggregator, INTEGERS_100);
    }

    @Test
    public void sumJava8AggregatorWithBigAmountOfNumbers() {
        runSumWith(java8Aggregator, INTEGERS_20_000);
    }

    @Test
    public void sumJava7ParallelAggregatorWithSmallAmountOfNumbers() {
        runSumWith(java7ParallelAggregator, INTEGERS_100);
    }

    @Test
    public void sumJava7ParallelAggregatorWithBigAmountOfNumbers() {
        runSumWith(java7ParallelAggregator, INTEGERS_20_000);
    }

    @Test
    public void sumJava8ParallelAggregatorWithSmallAmountOfNumbers() {
        runSumWith(java8ParallelAggregator, INTEGERS_100);
    }

    @Test
    public void sumJava8ParallelAggregatorWithBigAmountOfNumbers() {
        runSumWith(java8ParallelAggregator, INTEGERS_20_000);
    }

    @Test
    public void getMostFrequentWords() {
    }

    @Test
    public void getDuplicates() {
    }
}