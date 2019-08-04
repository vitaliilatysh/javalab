package com.epam.cdp.hw1.aggregator;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static com.epam.cdp.hw1.aggregator.utils.JavaAggregatorUtils.*;

public class Java7ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        ForkJoinTask<Integer> task = new SumForkJoin(numbers);
        return new ForkJoinPool().invoke(task);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        ForkJoinTask<List<Pair<String, Long>>> task = new FrequentWordsForkJoin(words, limit);
        return new ForkJoinPool().invoke(task);
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        ForkJoinTask<List<String>> task = new DuplicatesForkJoin(words, limit);
        return new ForkJoinPool().invoke(task);
    }

    public static class SumForkJoin extends RecursiveTask<Integer> {

        private final List<Integer> numbers;
        private final int start;
        private final int end;

        private SumForkJoin(List<Integer> numbers) {
            this(numbers, 0, numbers.size());
        }

        private SumForkJoin(List<Integer> numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {

            int length = end - start;
            if (length <= 2000) {
                return add();
            }

            SumForkJoin firstTask = new SumForkJoin(numbers, start, start + length / 2);
            firstTask.fork();

            SumForkJoin secondTask = new SumForkJoin(numbers, start + length / 2, end);

            int secondTaskResult = secondTask.compute();
            int firstTaskResult = firstTask.join();

            return firstTaskResult + secondTaskResult;

        }

        private int add() {
            int result = 0;
            for (int i = start; i < end; i++) {
                result += numbers.get(i);
            }
            return result;
        }

    }

    public static class FrequentWordsForkJoin extends RecursiveTask<List<Pair<String, Long>>> {

        private final List<String> words;
        private final int start;
        private final int end;
        private long limit;

        private FrequentWordsForkJoin(List<String> words, long limit) {
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
            if (length <= 2000) {
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
            for (int i = start; i < end; i++) {
                String word = words.get(i);
                wordCount.put(word, wordCount.get(word) == null ? 1L : wordCount.get(word) + 1);

            }

            comparingByValue(wordCount);

            return getFrequentWordsByLimit(limit, wordCount);
        }

    }

    public static class DuplicatesForkJoin extends RecursiveTask<List<String>> {

        private final List<String> words;
        private final int start;
        private final int end;
        private long limit;

        private DuplicatesForkJoin(List<String> words, long limit) {
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
            if (length <= 2000) {
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
            Map<String, Long> wordCount = new HashMap<>();
            for (int i = start; i < end; i++) {
                String word = words.get(i).toUpperCase();
                wordCount.put(word, wordCount.get(word) == null ? 1L : wordCount.get(word) + 1);
            }

            List<String> duplicates = mapDuplicates(wordCount);

            duplicates.sort(stringComparator);

            return getDuplicatesByLimit(limit, duplicates);
        }

    }
}
