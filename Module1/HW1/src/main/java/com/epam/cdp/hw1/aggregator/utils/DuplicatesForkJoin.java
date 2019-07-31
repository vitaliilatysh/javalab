package com.epam.cdp.hw1.aggregator.utils;

import java.util.*;
import java.util.concurrent.RecursiveTask;

import static com.epam.cdp.hw1.aggregator.utils.Java7AggregatorUtils.stringComparator;

public class DuplicatesForkJoin extends RecursiveTask<List<String>>{

    private final List<String> words;
    private final int start;
    private final int end;
    private long limit;

    public DuplicatesForkJoin(List<String> words, long limit) {
        this(words, 0, words.size(), limit);
    }

    private DuplicatesForkJoin(List<String> words, int start, int end, long limit) {
        this.words = words;
        this.start = start;
        this.end = end;
        this.limit = limit;
    }

    private DuplicatesForkJoin(List<String> words, int start, int end) {
        this.words = words;
        this.start = start;
        this.end = end;
    }


    @Override
    protected List<String> compute() {

        int length = end - start;
        if (length <= 1) {
            return getDuplicates();
        }

        DuplicatesForkJoin firstTask = new DuplicatesForkJoin(words, start, start + length / 2, limit);
        firstTask.fork();

        DuplicatesForkJoin secondTask = new DuplicatesForkJoin(words, start + length / 2, end);

        List<String> secondTaskResult = secondTask.compute();
        List<String> firstTaskResult = firstTask.join();

        secondTaskResult.addAll(firstTaskResult);

        return secondTaskResult;

    }

    private List<String> getDuplicates() {
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
