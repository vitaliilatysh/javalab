package com.epam.cdp.hw1.aggregator.duplicates.impl;

import com.epam.cdp.hw1.aggregator.Java8ParallelAggregator;
import com.epam.cdp.hw1.aggregator.duplicates.JavaAggregatorDuplicatesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java8ParallelAggregatorDuplicatesTest extends JavaAggregatorDuplicatesTest {

    public Java8ParallelAggregatorDuplicatesTest() {
        super(new Java8ParallelAggregator());
    }

}
