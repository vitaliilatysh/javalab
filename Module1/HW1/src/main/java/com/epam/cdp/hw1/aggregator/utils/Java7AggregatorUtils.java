package com.epam.cdp.hw1.aggregator.utils;

import java.util.*;

/**
 * @author Vitalii Latysh
 * Created: 31.07.2019
 */
public class Java7AggregatorUtils {

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
     * @param unsortedMap unsorted most frequent words
     * @return sorted words by frequency, if frequency the same then sorted alphabetically
     */
    public static Map<String, Long> comparingByValue(Map<String, Long> unsortedMap) {
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


}
