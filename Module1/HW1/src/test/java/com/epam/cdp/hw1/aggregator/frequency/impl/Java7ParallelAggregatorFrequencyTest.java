package com.epam.cdp.hw1.aggregator.frequency.impl;

import com.epam.cdp.hw1.aggregator.Java7ParallelAggregator;
import com.epam.cdp.hw1.aggregator.frequency.JavaAggregatorFrequencyTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java7ParallelAggregatorFrequencyTest extends JavaAggregatorFrequencyTest {

    public Java7ParallelAggregatorFrequencyTest() {
        super(new Java7ParallelAggregator());
    }
}
