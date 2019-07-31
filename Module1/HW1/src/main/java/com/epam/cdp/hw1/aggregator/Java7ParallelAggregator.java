package com.epam.cdp.hw1.aggregator;


import com.epam.cdp.hw1.aggregator.utils.Java7ParallelAggregatorForkJoin;
import com.epam.cdp.hw1.aggregator.utils.Java7ParallelAggregatorMostFrequentWordsForkJoin;
import javafx.util.Pair;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Java7ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        ForkJoinTask<Integer> task = new Java7ParallelAggregatorForkJoin(numbers);
        return new ForkJoinPool().invoke(task);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        ForkJoinTask<List<Pair<String, Long>>> task = new Java7ParallelAggregatorMostFrequentWordsForkJoin(words, limit);
        return new ForkJoinPool().invoke(task);
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }
}
