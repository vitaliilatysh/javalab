package com.epam.cdp.hw1.aggregator.sum;

import com.epam.cdp.hw1.aggregator.Aggregator;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public abstract class JavaAggregatorSumTest {

    @Parameterized.Parameter(0)
    public List<Integer> numbers;

    @Parameterized.Parameter(1)
    public int expected;

    private Aggregator aggregator;

    public JavaAggregatorSumTest(Aggregator aggregator) {
        this.aggregator = aggregator;
    }

    /**
     *
     * @return test data
     */
    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{asList(1, 2, 3, 4, 5, 6, 7, 8), 36});
        data.add(new Object[]{asList(10, -10, 3), 3});
        data.add(new Object[]{emptyList(), 0});
        return data;
    }

    @Test
    public void test() {
        int actual = aggregator.sum(numbers);
        assertEquals(expected, actual);
    }
}