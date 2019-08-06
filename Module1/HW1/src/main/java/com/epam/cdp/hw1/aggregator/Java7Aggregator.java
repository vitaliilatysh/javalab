package com.epam.cdp.hw1.aggregator;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

import static com.epam.cdp.hw1.aggregator.utils.JavaAggregatorUtils.*;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            if (number != null) {
                sum += number;
            }
        }
        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Map<String, Long> wordCount = createMapOfTheRepeatableWords(words);

        comparingByValue(wordCount);

        return getFrequentWordsByLimit(limit, wordCount);
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Map<String, Long> wordCount = createMapOfTheRepeatableWords(words);

        List<String> duplicates = mapDuplicates(wordCount);

        duplicates.sort(stringComparator);

        return getDuplicatesByLimit(limit, duplicates);
    }
}
