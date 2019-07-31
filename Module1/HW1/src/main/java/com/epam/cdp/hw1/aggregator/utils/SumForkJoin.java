package com.epam.cdp.hw1.aggregator.utils;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SumForkJoin extends RecursiveTask<Integer> {

    private final List<Integer> numbers;
    private final int start;
    private final int end;

    public SumForkJoin(List<Integer> numbers) {
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
        if (length <= 2) {
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
