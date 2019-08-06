package com.epam.cdp.hw1.aggregator.frequency.impl;

import com.epam.cdp.hw1.aggregator.Java8ParallelAggregator;
import com.epam.cdp.hw1.aggregator.frequency.JavaAggregatorFrequencyTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java8ParallelAggregatorFrequencyTest extends JavaAggregatorFrequencyTest {

    public Java8ParallelAggregatorFrequencyTest() {
        super(new Java8ParallelAggregator());
    }
}
