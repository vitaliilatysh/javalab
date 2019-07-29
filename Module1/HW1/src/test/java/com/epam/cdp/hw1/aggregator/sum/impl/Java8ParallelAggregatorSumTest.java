package com.epam.cdp.hw1.aggregator.sum.impl;

import com.epam.cdp.hw1.aggregator.Java8ParallelAggregator;
import com.epam.cdp.hw1.aggregator.sum.JavaAggregatorSumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java8ParallelAggregatorSumTest extends JavaAggregatorSumTest {

    public Java8ParallelAggregatorSumTest() {
        super(new Java8ParallelAggregator());
    }
}
