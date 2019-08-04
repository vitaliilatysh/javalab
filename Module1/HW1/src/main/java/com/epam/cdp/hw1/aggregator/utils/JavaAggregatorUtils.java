package com.epam.cdp.hw1.aggregator.utils;

import javafx.util.Pair;

import java.util.*;
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
public class JavaAggregatorUtils {

    public static Comparator<String> stringComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int result;
            result = o1.length() - o2.length();
            if (result == 0) {
                result = o1.charAt(0) - o2.charAt(0);
            }
            return result;
        }
    };

    /**
     * This method is used for sorting the words by their frequency,
     * if frequency the same then sort alphabetically
     *
     * @param unsortedMap unsorted most frequent words
     * @return sorted words
     */
    public static Map<String, Long> comparingByValue(Map<String, Long> unsortedMap) {
        List<Map.Entry<String, Long>> list = new LinkedList<>(unsortedMap.entrySet());

        list.sort(new Comparator<Map.Entry<String, Long>>() {
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                int result;
                result = o1.getValue().compareTo(o2.getValue());
                if (result == 0) {
                    result = o1.getKey().charAt(0) - o2.getKey().charAt(0);
                }
                return result;
            }
        });

        Map<String, Long> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : list) {
            temp.put(entry.getKey(), entry.getValue());
        }
        return temp;
    }

    /**
     * Gathering words into map where the key = word, value = appearance time
     *
     * @param words any amount of words
     * @return map of words
     */
    public static Map<String, Long> createMapOfTheRepeatableWords(final List<String> words) {
        final Map<String, Long> wordCount = new HashMap<>();
        for (final String word : words) {
            if (word != null) {
                String wordKey = word.toUpperCase();
                wordCount.put(wordKey, wordCount.get(wordKey) == null ? 1L : wordCount.get(wordKey) + 1);
            }
        }
        return wordCount;
    }

    /**
     * Getting the list of duplicated words
     *
     * @param limit      how many words are getting to result
     * @param duplicates list of duplicated words
     * @return limited list of duplicated words
     */
    public static List<String> getDuplicatesByLimit(long limit, List<String> duplicates) {
        List<String> result = new ArrayList<>();
        for (String wordFromResult : duplicates) {
            if (limit == 0) {
                break;
            }
            result.add(wordFromResult);
            limit--;
        }
        return result;
    }

    /**
     * Getting the duplicated words
     *
     * @param wordCount map of words
     * @return only words that have more than 1 appearance
     */
    public static List<String> mapDuplicates(Map<String, Long> wordCount) {
        List<String> duplicates = new ArrayList<>();
        for (Map.Entry<String, Long> entry : wordCount.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.add(entry.getKey());
            }
        }
        return duplicates;
    }

    /**
     * Getting the list of words with their frequency
     *
     * @param limit     how many words are getting to result
     * @param wordCount map of words and their frequency
     * @return list of pair objects
     */
    public static List<Pair<String, Long>> getFrequentWordsByLimit(long limit, final Map<String, Long> wordCount) {
        final List<Pair<String, Long>> result = new ArrayList<>();
        for (Map.Entry<String, Long> entry : wordCount.entrySet()) {
            if (limit == 0) {
                break;
            }
            result.add(new Pair<>(entry.getKey().toLowerCase(), entry.getValue()));
            limit--;
        }
        return result;
    }

    /**
     * Getting sum of integer numbers
     *
     * @param numberStream stream of numbers
     * @return sum
     */
    public static int getSum(final Stream<Integer> numberStream) {
        return numberStream
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();
    }

    /**
     * Getting the most frequently used words
     *
     * @param limit     max number of elements to display
     * @param wordCount stream of strings
     * @return Pair object that contains word itself and its frequency
     */
    public static List<Pair<String, Long>> getPairs(final long limit, final Stream<String> wordCount) {
        final Map<String, Long> result = wordCount.filter(Objects::nonNull)
                .collect(groupingBy(Function.identity(), counting()));

        return result.entrySet().stream()
                .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    /**
     * Getting sorted duplicated strings by length, if the length is equaled, then
     * sorted alphabetically.
     *
     * @param stringStream stream of strings
     * @param limit        max number of elements to display
     * @return sorted duplicated strings
     */
    public static List<String> getDuplicatedStrings(final Stream<String> stringStream, final long limit) {
        final Map<String, Long> duplicates = stringStream
                .map(String::toUpperCase)
                .collect(groupingBy(Function.identity(), counting()));

        return duplicates.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparing(String::length).thenComparing(naturalOrder()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
