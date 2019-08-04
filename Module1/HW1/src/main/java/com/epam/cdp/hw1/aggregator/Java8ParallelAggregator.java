package com.epam.cdp.hw1.aggregator;

import javafx.util.Pair;

import java.util.List;
import java.util.stream.Stream;

import static com.epam.cdp.hw1.aggregator.utils.JavaAggregatorUtils.*;

public class Java8ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        Stream<Integer> numberStream = numbers.parallelStream();
        return getSum(numberStream);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Stream<String> wordCount = words.parallelStream();
        return getPairs(limit, wordCount);
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Stream<String> stringStream = words.parallelStream();
        return getDuplicatedStrings(stringStream, limit);
    }
}
