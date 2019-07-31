package com.epam.cdp.hw1.aggregator.utils;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author Vitalii Latysh
 * Created: 31.07.2019
 */
public class Java8AggregatorUtils {

    public static int getSum(Stream<Integer> numberStream){
        return numberStream
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static List<Pair<String, Long>> getPairs(long limit, Stream<String> wordCount) {
        Map<String, Long> res = wordCount.filter(Objects::nonNull)
                .collect(groupingBy(Function.identity(), counting()));

        return res.entrySet().stream()
                .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public static List<String> getDuplicatedStrings(Stream<String> stringStream, long limit) {
        Map<String, Long> duplicates = stringStream
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return duplicates.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparing(String::length).thenComparing(naturalOrder()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
