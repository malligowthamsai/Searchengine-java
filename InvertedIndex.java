package indexer;

import java.util.*;

public class InvertedIndex {

    private Map<String, Set<String>> index = new HashMap<>();

    public void buildIndex(Map<String, String> pages) {

        for (String url : pages.keySet()) {

            String content = pages.get(url);

            String[] words = content.toLowerCase().split("\\s+");

            for (String word : words) {

                if (word.isEmpty()) continue;

                index.putIfAbsent(word, new HashSet<>());
                index.get(word).add(url);
            }
        }
    }

    public Set<String> search(String word) {
        return index.getOrDefault(word.toLowerCase(), new HashSet<>());
    }
}
