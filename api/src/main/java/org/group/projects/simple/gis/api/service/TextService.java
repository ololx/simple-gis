package org.group.projects.simple.gis.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface TextService {

    static int getLevenstainDistance(final String word, final String wordOther) {
        int[] Di_1 = new int[wordOther.length() + 1];
        int[] Di = new int[wordOther.length() + 1];

        for (int j = 0; j <= wordOther.length(); j++) {
            Di[j] = j;
        }

        for (int i = 1; i <= word.length(); i++) {
            System.arraycopy(Di, 0, Di_1, 0, Di_1.length);

            Di[0] = i;
            for (int j = 1; j <= wordOther.length(); j++) {
                int cost = (word.charAt(i - 1) != wordOther.charAt(j - 1)) ? 1 : 0;
                Di[j] = Math.min(Math.min(Di_1[j] + 1, Di[j - 1] + 1), Di_1[j - 1] + cost);
            }
        }

        return Di[Di.length - 1];
    }

    static int keyWordsComare(String request, String response) {
        List<String> requestWords = parseWord(request);
        List<String> responseWords = parseWord(response);

        int levensteinDistance = 0;

        for(int i = 0; i < requestWords.size(); i++){
            int lCount = lCount = request.length();

            for(int j = 0; j < responseWords.size(); j++) {
                lCount = Math.min(getLevenstainDistance(requestWords.get(i), responseWords.get(j)),
                        lCount);
            }

            levensteinDistance += lCount;
        }

        return levensteinDistance;
    }

    static List<String> parseWord(String string) {
        Pattern pattern = Pattern.compile("[a-zа-яA-ZА-Я\\d]+");
        Matcher matcher = pattern.matcher(string.trim());

        return new ArrayList<String>(){{
            while(matcher.find()) {
                add(matcher.group());
            }
        }};
    }

    static List<String> parseNGrams(String string, int n) {
        Pattern pattern = Pattern.compile(String.format("(?<=\\G.{%s})", n));

        return Stream.of(string.trim().split(pattern.toString()))
                .parallel()
                .collect(Collectors.toList());
    }

    static String getKeywords(String request) {

        String result = parseWord(request).stream()
                .parallel()
                .map(keyWord -> String.format(">%s <(%s)", keyWord,
                        parseNGrams(keyWord, 2).stream()
                                .parallel()
                                .map(biGramm -> String.format("*%s*", biGramm))
                                .collect(Collectors.joining(" "))))
                .collect(Collectors.joining(" "));

        return result;
    }
}
