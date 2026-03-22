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

        Set<String> results = new HashSet<>();

        for (String word : words) {

            Set<String> urls = index.search(word);

            if (results.isEmpty()) {
                results.addAll(urls);
            } else {
                results.retainAll(urls); // AND logic
            }
        }

        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            System.out.println("Results:");
            results.forEach(System.out::println);
        }
    }
}
