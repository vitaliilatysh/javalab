package com.epam.cdp.hw1.aggregator.performance;

import com.epam.cdp.hw1.aggregator.Java7Aggregator;
import com.epam.cdp.hw1.aggregator.Java8Aggregator;
import com.epam.cdp.hw1.aggregator.Java8ParallelAggregator;
import com.epam.cdp.hw1.aggregator.sum.impl.Java7AggregatorSumTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;

public class JavaSumPerformanceTest {

    private Java7Aggregator java7Aggregator = new Java7Aggregator();
    private Java8Aggregator java8Aggregator = new Java8Aggregator();
    private Java8ParallelAggregator java8ParallelAggregator = new Java8ParallelAggregator();

    private final static List<Integer> integers= new ArrayList<>();

    static {
        Random r = new Random();
        for(int i = 1; i <= 1000000; i++) {
            integers.add(r.ints(1, (1000 + 1)).findFirst().getAsInt());
        }
    }

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
        java8Aggregator.sum(integers);
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