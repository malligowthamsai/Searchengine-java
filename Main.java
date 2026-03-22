package app;

import crawler.WebCrawler;
import indexer.InvertedIndex;
import search.SearchEngine;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String startUrl = "https://example.com";

        System.out.println("Starting crawler...");

        Map<String, String> pages = WebCrawler.crawl(startUrl, 5);

        System.out.println("\nBuilding index...");

        InvertedIndex index = new InvertedIndex();
        index.buildIndex(pages);

        SearchEngine engine = new SearchEngine(index);

        while (true) {

            System.out.print("\nSearch: ");
            String query = scanner.nextLine();

            if (query.equalsIgnoreCase("exit")) break;

            engine.search(query);
        }
    }
}
