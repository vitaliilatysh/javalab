package com.epam.cdp.hw1.aggregator;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.cdp.hw1.aggregator.utils.JavaAggregatorUtils.*;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Java8ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        Stream<Integer> numberStream = numbers.parallelStream();
        return getSum(numberStream);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        return words.parallelStream().filter(Objects::nonNull)
                .collect(groupingBy(Function.identity(), counting())).entrySet().parallelStream()
                .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        return words.parallelStream()
                .map(String::toUpperCase)
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet().parallelStream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparing(String::length).thenComparing(naturalOrder()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
