package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;

public class WebCrawler {

    public static Map<String, String> crawl(String startUrl, int maxPages) {

        Map<String, String> pages = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(startUrl);

        while (!queue.isEmpty() && pages.size() < maxPages) {

            String url = queue.poll();

            if (visited.contains(url)) continue;

            try {
                Document doc = Jsoup.connect(url).get();

                System.out.println("Crawled: " + url);

                pages.put(url, doc.text());
                visited.add(url);

                Elements links = doc.select("a[href]");

                links.stream()
                        .map(link -> link.attr("abs:href"))
                        .filter(link -> !visited.contains(link))
                        .limit(5)
                        .forEach(queue::add);

            } catch (Exception e) {
                System.out.println("Failed: " + url);
            }
        }

        return pages;
    }
}
