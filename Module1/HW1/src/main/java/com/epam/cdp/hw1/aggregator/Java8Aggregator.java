package com.epam.cdp.hw1.aggregator;

import javafx.util.Pair;

import java.util.List;
import java.util.stream.Stream;

import static com.epam.cdp.hw1.aggregator.utils.Java8AggregatorUtils.getDuplicatedStrings;
import static com.epam.cdp.hw1.aggregator.utils.Java8AggregatorUtils.getPairs;
import static com.epam.cdp.hw1.aggregator.utils.Java8AggregatorUtils.getSum;

public class Java8Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        Stream<Integer> numberStream = numbers.stream();
        return getSum(numberStream);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Stream<String> wordCount = words.stream();
        return getPairs(limit, wordCount);
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Stream<String> stringStream = words.stream();
        return getDuplicatedStrings(stringStream, limit);
    }
}