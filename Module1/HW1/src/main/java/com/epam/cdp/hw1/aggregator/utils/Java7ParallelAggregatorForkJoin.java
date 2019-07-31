package com.epam.cdp.hw1.aggregator.utils;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Java7ParallelAggregatorForkJoin extends RecursiveTask<Integer> {

    private final List<Integer> numbers;
    private final int start;
    private final int end;

    public Java7ParallelAggregatorForkJoin(List<Integer> numbers) {
        this(numbers, 0, numbers.size());
    }

    private Java7ParallelAggregatorForkJoin(List<Integer> numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        int length = end - start;
        if (length <= 2) {
            return add();
        }

        Java7ParallelAggregatorForkJoin firstTask = new Java7ParallelAggregatorForkJoin(numbers, start, start + length / 2);
        firstTask.fork();

        Java7ParallelAggregatorForkJoin secondTask = new Java7ParallelAggregatorForkJoin(numbers, start + length / 2, end);

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
