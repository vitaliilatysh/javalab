package com.epam.cdp.hw1.aggregator.duplicates.impl;

import com.epam.cdp.hw1.aggregator.Java7ParallelAggregator;
import com.epam.cdp.hw1.aggregator.duplicates.JavaAggregatorDuplicatesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java7ParallelAggregatorDuplicatesTest extends JavaAggregatorDuplicatesTest {

    public Java7ParallelAggregatorDuplicatesTest() {
        super(new Java7ParallelAggregator());
    }
}
