package com.epam.cdp.m2.hw2.aggregator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javafx.util.Pair;

public class Java8Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("numbers is null");
        }
        if (numbers.isEmpty()) {
            return 0;
        }

        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        TreeMap<String, Long> map = new TreeMap<>();

        for (String t : words) {
            Long val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        return map.entrySet().stream()
        .map(e -> new Pair<>(e.getKey(), e.getValue()))
        .limit(limit)
        .collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String,Integer>>() {
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

        Map<String, Integer> duplicates = map.entrySet().stream()
        .filter(entry -> entry.getValue() > 1)
        .sorted(valueComparator)
        .limit(limit)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        

        return new ArrayList<>(duplicates.keySet()); 
    }
}