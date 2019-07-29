package com.epam.cdp.hw1.aggregator.sum.impl;

import com.epam.cdp.hw1.aggregator.Java7Aggregator;
import com.epam.cdp.hw1.aggregator.sum.JavaAggregatorSumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java7AggregatorSumTest extends JavaAggregatorSumTest {

    public Java7AggregatorSumTest() {
        super(new Java7Aggregator());
    }

}
