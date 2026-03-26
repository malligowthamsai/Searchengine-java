package indexer;

import java.util.*;

public class InvertedIndex {

    private Map<String, Map<String, Integer>> index = new HashMap<>();

    public void buildIndex(Map<String, String> pages) {

        for (String url : pages.keySet()) {

            String content = pages.get(url).toLowerCase();
            String[] words = content.split("\\s+");

            for (String word : words) {

                if (word.isEmpty()) continue;

                index.putIfAbsent(word, new HashMap<>());
                Map<String, Integer> urlMap = index.get(word);

                urlMap.put(url, urlMap.getOrDefault(url, 0) + 1);
            }
        }
    }

    public Map<String, Integer> search(String word) {
        return index.getOrDefault(word.toLowerCase(), new HashMap<>());
    }
}
