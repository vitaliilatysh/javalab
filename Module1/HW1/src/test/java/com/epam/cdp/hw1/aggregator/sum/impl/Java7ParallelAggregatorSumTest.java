package com.epam.cdp.hw1.aggregator.sum.impl;

import com.epam.cdp.hw1.aggregator.Java7ParallelAggregator;
import com.epam.cdp.hw1.aggregator.sum.JavaAggregatorSumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java7ParallelAggregatorSumTest extends JavaAggregatorSumTest {

    public Java7ParallelAggregatorSumTest() {
        super(new Java7ParallelAggregator());
    }
}
