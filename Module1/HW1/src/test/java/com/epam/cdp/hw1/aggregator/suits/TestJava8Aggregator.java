package com.epam.cdp.hw1.aggregator.suits;

import com.epam.cdp.hw1.aggregator.duplicates.impl.Java8AggregatorDuplicatesTest;
import com.epam.cdp.hw1.aggregator.frequency.impl.Java8AggregatorFrequencyTest;
import com.epam.cdp.hw1.aggregator.sum.impl.Java8AggregatorSumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Java8AggregatorFrequencyTest.class,
        Java8AggregatorSumTest.class,
        Java8AggregatorDuplicatesTest.class,
})
public class TestJava8Aggregator {
}
