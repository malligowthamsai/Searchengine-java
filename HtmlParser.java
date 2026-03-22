package parser;

import java.util.*;

public class HtmlParser {

    public static List<String> parseWords(String content) {

        content = content.toLowerCase().replaceAll("[^a-zA-Z ]", "");

        String[] words = content.split("\\s+");

        return Arrays.asList(words);
    }
}
