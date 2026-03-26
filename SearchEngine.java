package search;

import indexer.InvertedIndex;

import java.util.*;

public class SearchEngine {

    private InvertedIndex index;

    public SearchEngine(InvertedIndex index) {
        this.index = index;
    }

    public void search(String query) {

        String[] words = query.toLowerCase().split("\\s+");

        Map<String, Integer> scores = new HashMap<>();

        for (String word : words) {

            Map<String, Integer> results = index.search(word);

            for (String url : results.keySet()) {
                scores.put(url, scores.getOrDefault(url, 0) + results.get(url));
            }
        }

        List<Map.Entry<String, Integer>> ranked = new ArrayList<>(scores.entrySet());

        ranked.sort((a, b) -> b.getValue() - a.getValue());

        if (ranked.isEmpty()) {
            System.out.println("No results found.");
        } else {
            System.out.println("Top Results:");
            for (Map.Entry<String, Integer> entry : ranked) {
                System.out.println(entry.getKey() + " (score: " + entry.getValue() + ")");
            }
        }
    }
}
