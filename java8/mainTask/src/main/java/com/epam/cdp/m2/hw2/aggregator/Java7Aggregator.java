package com.epam.cdp.m2.hw2.aggregator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import javafx.util.Pair;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("numbers is null");
        }
        if (numbers.isEmpty()) {
            return 0;
        }

        int sum = 0;

        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        TreeMap<String, Long> map = new TreeMap<>();

        for (String t : words) {
            Long val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        List<Pair<String, Long>> list = new ArrayList<>();

        int i = 0;
        for (Entry<String, Long> entry : map.entrySet()) {
            if (i < limit) {
                list.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
            i++;
        }

        return list;
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                Integer len1 = e1.getKey().length();
                Integer len2 = e2.getKey().length();
                if (Objects.equals(len1, len2)) {
                    String str1 = e1.getKey();
                    String str2 = e2.getKey();
                    return str1.compareTo(str2);
                }
                return len1.compareTo(len2);
            }
        };

        TreeMap<String, Integer> map = new TreeMap<>();

        for (String t : words) {
            String key = t.toUpperCase();
            Integer val = map.get(key);
            map.put(key, val == null ? 1 : val + 1);
        }

        Set<Entry<String, Integer>> entries = map.entrySet();

        List<Entry<String, Integer>> listOfEntries = new ArrayList<>(entries);
        Collections.sort(listOfEntries, valueComparator);

        List<String> result = new ArrayList<>();

        for (Entry<String, Integer> entry : listOfEntries) {
            if (result.size() >= limit) {
                break;
            }
            if (entry.getValue() >= 2) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}
