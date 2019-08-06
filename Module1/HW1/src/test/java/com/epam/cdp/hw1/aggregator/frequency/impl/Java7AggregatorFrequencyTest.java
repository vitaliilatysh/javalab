package com.epam.cdp.hw1.aggregator.frequency.impl;

import com.epam.cdp.hw1.aggregator.Java7Aggregator;
import com.epam.cdp.hw1.aggregator.frequency.JavaAggregatorFrequencyTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java7AggregatorFrequencyTest extends JavaAggregatorFrequencyTest {

    public Java7AggregatorFrequencyTest() {
        super(new Java7Aggregator());
    }

}
