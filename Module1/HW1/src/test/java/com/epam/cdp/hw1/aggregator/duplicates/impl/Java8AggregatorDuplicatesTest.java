package com.epam.cdp.hw1.aggregator.duplicates.impl;

import com.epam.cdp.hw1.aggregator.Java8Aggregator;
import com.epam.cdp.hw1.aggregator.duplicates.JavaAggregatorDuplicatesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java8AggregatorDuplicatesTest extends JavaAggregatorDuplicatesTest {

    public Java8AggregatorDuplicatesTest() {
        super(new Java8Aggregator());
    }
}

