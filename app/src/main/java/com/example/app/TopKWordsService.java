package com.example.app;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

@Service
public class TopKWordsService {

    public List<String> findTopKWords(MultipartFile file, int k) throws IOException {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\W+");
                for (String word : words) {
                    word = word.toLowerCase();
                    wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
                }
            }
        }

        // Create a max heap (priority queue) to store the most frequent words
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(
                (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())
        );

        // Iterate over the wordFrequencyMap and add entries to the max heap
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            maxHeap.offer(entry);
        }

        // Create a result array to store the top K most frequent words
        List<String> topKWords = new ArrayList<>();

        // Extract the top K most frequent words from the max heap
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            Map.Entry<String, Integer> entry = maxHeap.poll();
            System.out.println(entry);
            topKWords.add(entry.getKey());
        }

        return topKWords;
    }
}
