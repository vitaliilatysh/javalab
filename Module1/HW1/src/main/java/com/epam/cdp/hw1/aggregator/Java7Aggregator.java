package com.epam.cdp.hw1.aggregator;

import javafx.util.Pair;

import java.util.*;

import static com.epam.cdp.hw1.aggregator.utils.Java7AggregatorUtils.comparingByValue;
import static com.epam.cdp.hw1.aggregator.utils.Java7AggregatorUtils.stringComparator;

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
        Map<String, Long> wordCount = new HashMap<>();
        for (String word : words) {
            if (word != null) {
                wordCount.put(word, wordCount.get(word) == null ? 1L : wordCount.get(word) + 1);
            }
        }

        comparingByValue(wordCount);

        List<Pair<String, Long>> result = new ArrayList<>();
        for (Map.Entry<String, Long> entry : wordCount.entrySet()) {
            if (limit != 0) {
                result.add(new Pair<>(entry.getKey(), entry.getValue()));
                limit--;
            } else {
                break;
            }
        }
        return result;
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Set<String> allWords = new HashSet<>();
        List<String> duplicates = new ArrayList<>();
        for (String word : words) {
            if (word != null) {
                String uniqueWord = word.toUpperCase();
                if (!allWords.add(uniqueWord)) {
                    duplicates.add(word.toUpperCase());
                }
            }
        }

        duplicates.sort(stringComparator);

        List<String> showDuplicatesByLimit = new ArrayList<>();
        for (String wordFromResult : duplicates) {
            if (limit != 0) {
                showDuplicatesByLimit.add(wordFromResult);
                limit--;
            } else {
                break;
            }
        }
        return showDuplicatesByLimit;
    }
}
