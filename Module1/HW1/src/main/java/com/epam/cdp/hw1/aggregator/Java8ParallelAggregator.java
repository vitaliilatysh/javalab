package com.epam.cdp.hw1.aggregator;

import com.epam.cdp.hw1.aggregator.utils.Java8AggregatorUtils;
import javafx.util.Pair;

import java.util.List;
import java.util.stream.Stream;

import static com.epam.cdp.hw1.aggregator.utils.Java8AggregatorUtils.getSum;

public class Java8ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        Stream<Integer> numberStream = numbers.parallelStream();
        return getSum(numberStream);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Stream<String> wordCount =  words.parallelStream();
        return Java8AggregatorUtils.getPairs(limit, wordCount);
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Stream<String> stringStream = words.parallelStream();
        return Java8AggregatorUtils.getDuplicatedStrings(stringStream, limit);
    }
}
