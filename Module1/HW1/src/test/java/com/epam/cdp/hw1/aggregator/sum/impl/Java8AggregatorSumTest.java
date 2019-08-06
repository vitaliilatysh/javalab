package com.epam.cdp.hw1.aggregator.sum.impl;

import com.epam.cdp.hw1.aggregator.Java8Aggregator;
import com.epam.cdp.hw1.aggregator.sum.JavaAggregatorSumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java8AggregatorSumTest extends JavaAggregatorSumTest {

    public Java8AggregatorSumTest() {
        super(new Java8Aggregator());
    }
}

