package com.epam.cdp.hw1.aggregator;

import javafx.util.Pair;

import java.util.*;

public class Java7Aggregator implements Aggregator {

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
            if (word != null && wordCount.containsKey(word)) {
                long count = wordCount.get(word);
                wordCount.put(word, ++count);
            } else {
                wordCount.put(word, 1L);
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

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Set<String> allWords = new HashSet<>();
        List<String> duplicates = new ArrayList<>();
        for (String word : words) {
            if (word != null) {
                String uniqueWord = word.toUpperCase();
                if(!allWords.add(uniqueWord)) {
                    duplicates.add(word.toUpperCase());
                }
            }
        }

        duplicates.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result;
                result = o1.length() - o2.length();
                if(result == 0){
                    result = o1.charAt(0) - o2.charAt(0);
                }
                return result;
            }
        });

        List<String> showDuplicatesByLimit = new ArrayList<>();
        for (String wordFromResult : duplicates) {
            if (limit != 0) {
                showDuplicatesByLimit.add(wordFromResult);
                limit--;
            }
        }
        return showDuplicatesByLimit;
    }
}
