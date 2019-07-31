package com.epam.cdp.hw1.aggregator.performance;

import com.epam.cdp.hw1.aggregator.Java7Aggregator;
import com.epam.cdp.hw1.aggregator.Java8Aggregator;
import com.epam.cdp.hw1.aggregator.Java8ParallelAggregator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JavaSumPerformanceTest {

    private static final List<Integer> integers = new ArrayList<>();

    static {
        Random random = new Random();
        for (int i = 1; i <= 10000000; i++) {
            integers.add(random.ints(1, (1000 + 1)).findFirst().getAsInt());
        }
    }

    private Java7Aggregator java7Aggregator = new Java7Aggregator();
    private Java8Aggregator java8Aggregator = new Java8Aggregator();
    private Java8ParallelAggregator java8ParallelAggregator = new Java8ParallelAggregator();

    @Test
    public void sum() {
        long start = System.currentTimeMillis();
        java7Aggregator.sum(integers);
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);

        long start1 = System.currentTimeMillis();
        java8Aggregator.sum(integers);
        long finish1 = System.currentTimeMillis();
        System.out.println(finish1 - start1);

        long start2 = System.currentTimeMillis();
        java8ParallelAggregator.sum(integers);
        long finish2 = System.currentTimeMillis();
        System.out.println(finish2 - start2);

    }

    @Test
    public void getMostFrequentWords() {
    }

    @Test
    public void getDuplicates() {
    }
}