package com.epam.cdp.hw1.aggregator.utils;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.RecursiveTask;

import static com.epam.cdp.hw1.aggregator.utils.Java7AggregatorUtils.comparingByValue;

public class FrequentWordsForkJoin extends RecursiveTask<List<Pair<String, Long>>>{

    private final List<String> words;
    private final int start;
    private final int end;
    private long limit;

    public FrequentWordsForkJoin(List<String> words, long limit) {
        this(words, 0, words.size(), limit);
    }

    private FrequentWordsForkJoin(List<String> words, int start, int end, long limit) {
        this.words = words;
        this.start = start;
        this.end = end;
        this.limit = limit;
    }

    private FrequentWordsForkJoin(List<String> words, int start, int end) {
        this.words = words;
        this.start = start;
        this.end = end;
    }


    @Override
    protected List<Pair<String, Long>> compute() {

        int length = end - start;
        if (length <= 1) {
            return getMostFrequentWords();
        }

        FrequentWordsForkJoin firstTask = new FrequentWordsForkJoin(words, start, start + length / 2, limit);
        firstTask.fork();

        FrequentWordsForkJoin secondTask = new FrequentWordsForkJoin(words, start + length / 2, end);

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

}
