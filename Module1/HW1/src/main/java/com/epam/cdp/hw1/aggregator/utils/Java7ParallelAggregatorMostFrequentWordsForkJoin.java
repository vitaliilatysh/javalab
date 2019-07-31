package com.epam.cdp.hw1.aggregator.utils;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.RecursiveTask;

public class Java7ParallelAggregatorMostFrequentWordsForkJoin extends RecursiveTask<List<Pair<String, Long>>>{

    private final List<String> words;
    private final int start;
    private final int end;
    private long limit;

    private static Map<String, Long> sortByValue(Map<String, Long> unsortedMap) {
        List<Map.Entry<String, Long>> list = new LinkedList<>(unsortedMap.entrySet());

        list.sort(new Comparator<Map.Entry<String, Long>>() {
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                int result;
                result = (o1.getValue()).compareTo(o2.getValue());
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

    public Java7ParallelAggregatorMostFrequentWordsForkJoin(List<String> words, long limit) {
        this(words, 0, words.size(), limit);
    }

    private Java7ParallelAggregatorMostFrequentWordsForkJoin(List<String> words, int start, int end, long limit) {
        this.words = words;
        this.start = start;
        this.end = end;
        this.limit = limit;
    }

    @Override
    protected List<Pair<String, Long>> compute() {

        int length = end - start;
        if (length <= 2) {
            return getMostFrequentWords();
        }

        Java7ParallelAggregatorMostFrequentWordsForkJoin firstTask = new Java7ParallelAggregatorMostFrequentWordsForkJoin(words, start, start + length / 2, limit);
        firstTask.fork();

        Java7ParallelAggregatorMostFrequentWordsForkJoin secondTask = new Java7ParallelAggregatorMostFrequentWordsForkJoin(words, start + length / 2, end, limit);

        List<Pair<String, Long>> secondTaskResult = secondTask.compute();
        List<Pair<String, Long>> firstTaskResult = firstTask.join();

        secondTaskResult.addAll(firstTaskResult);

        return secondTaskResult;

    }

    private List<Pair<String, Long>> getMostFrequentWords() {
        Map<String, Long> wordCount = new HashMap<>();
        for (String word : words) {
            if (word != null) {
                wordCount.put(word, wordCount.get(word) == null ? 1L : wordCount.get(word) + 1);
            }
        }
        sortByValue(wordCount);

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

}
