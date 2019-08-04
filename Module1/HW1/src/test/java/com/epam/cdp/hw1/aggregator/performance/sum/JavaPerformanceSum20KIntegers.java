package com.epam.cdp.hw1.aggregator.performance.sum;

import com.epam.cdp.hw1.aggregator.performance.JavaAggregatorPerformanceTest;
import org.junit.Test;

import java.util.Random;

public class JavaPerformanceSum20KIntegers extends JavaAggregatorPerformanceTest {

    static {
        Random random = new Random();
        for (int i = 1; i <= 20000; i++) {
            INTEGERS_20K.add(random.ints(1, (1000 + 1)).findFirst().getAsInt());
        }
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
}
