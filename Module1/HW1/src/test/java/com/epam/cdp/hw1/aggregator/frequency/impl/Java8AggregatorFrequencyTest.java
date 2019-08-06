package com.epam.cdp.hw1.aggregator.frequency.impl;

import com.epam.cdp.hw1.aggregator.Java8Aggregator;
import com.epam.cdp.hw1.aggregator.frequency.JavaAggregatorFrequencyTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java8AggregatorFrequencyTest extends JavaAggregatorFrequencyTest {

    public Java8AggregatorFrequencyTest() {
        super(new Java8Aggregator());
    }
}

