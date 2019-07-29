package com.epam.cdp.hw1.aggregator.suits;

import com.epam.cdp.hw1.aggregator.duplicates.impl.Java7AggregatorDuplicatesTest;
import com.epam.cdp.hw1.aggregator.duplicates.impl.Java7ParallelAggregatorDuplicatesTest;
import com.epam.cdp.hw1.aggregator.duplicates.impl.Java8AggregatorDuplicatesTest;
import com.epam.cdp.hw1.aggregator.duplicates.impl.Java8ParallelAggregatorDuplicatesTest;
import com.epam.cdp.hw1.aggregator.frequency.impl.Java7AggregatorFrequencyTest;
import com.epam.cdp.hw1.aggregator.frequency.impl.Java7ParallelAggregatorFrequencyTest;
import com.epam.cdp.hw1.aggregator.frequency.impl.Java8AggregatorFrequencyTest;
import com.epam.cdp.hw1.aggregator.frequency.impl.Java8ParallelAggregatorFrequencyTest;
import com.epam.cdp.hw1.aggregator.sum.impl.Java7AggregatorSumTest;
import com.epam.cdp.hw1.aggregator.sum.impl.Java7ParallelAggregatorSumTest;
import com.epam.cdp.hw1.aggregator.sum.impl.Java8AggregatorSumTest;
import com.epam.cdp.hw1.aggregator.sum.impl.Java8ParallelAggregatorSumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Java7AggregatorFrequencyTest.class,
        Java7AggregatorSumTest.class,
        Java7AggregatorDuplicatesTest.class,

        Java8AggregatorFrequencyTest.class,
        Java8AggregatorSumTest.class,
        Java8AggregatorDuplicatesTest.class,

        Java7ParallelAggregatorFrequencyTest.class,
        Java7ParallelAggregatorSumTest.class,
        Java7ParallelAggregatorDuplicatesTest.class,

        Java8ParallelAggregatorFrequencyTest.class,
        Java8ParallelAggregatorSumTest.class,
        Java8ParallelAggregatorDuplicatesTest.class,
})
public class TestAll {
}
