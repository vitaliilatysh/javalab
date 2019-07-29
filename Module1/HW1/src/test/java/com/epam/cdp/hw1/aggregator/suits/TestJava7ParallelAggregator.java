package com.epam.cdp.hw1.aggregator.suits;

import com.epam.cdp.hw1.aggregator.duplicates.impl.Java7ParallelAggregatorDuplicatesTest;
import com.epam.cdp.hw1.aggregator.frequency.impl.Java7ParallelAggregatorFrequencyTest;
import com.epam.cdp.hw1.aggregator.sum.impl.Java7ParallelAggregatorSumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Java7ParallelAggregatorFrequencyTest.class,
        Java7ParallelAggregatorSumTest.class,
        Java7ParallelAggregatorDuplicatesTest.class,
})
public class TestJava7ParallelAggregator {
}
