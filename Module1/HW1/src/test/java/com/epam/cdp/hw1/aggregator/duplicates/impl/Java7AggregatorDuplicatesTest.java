package com.epam.cdp.hw1.aggregator.duplicates.impl;

import com.epam.cdp.hw1.aggregator.Java7Aggregator;
import com.epam.cdp.hw1.aggregator.duplicates.JavaAggregatorDuplicatesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java7AggregatorDuplicatesTest extends JavaAggregatorDuplicatesTest {

    public Java7AggregatorDuplicatesTest() {
        super(new Java7Aggregator());
    }
}
