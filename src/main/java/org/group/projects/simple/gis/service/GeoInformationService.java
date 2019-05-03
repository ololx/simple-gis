package org.group.projects.simple.gis.service;

import org.group.projects.simple.gis.model.entity.Building;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface GeoInformationService {

    static String getBiGramms(String keyString) {

        return Stream.of(keyString.toLowerCase().split(" "))
                .parallel()
                .distinct()
                .map(eachKeyWord -> Stream.of(eachKeyWord.toLowerCase()
                        .split("(?<=\\G.{2})"))
                        .parallel()
                        .distinct()
                        .map(eachKeyCharacter -> String.format("*%s*", eachKeyCharacter))
                        .collect(Collectors.joining(" ")))
                .collect(Collectors.joining(" "));
    }

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

    static int compareStrings(Building word, Building wordOther) {
        return 1;
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

    static List<String> parseNGrams(String string) {
        Pattern pattern = Pattern.compile("(?<=\\G.{2})");

        return Stream.of(string.split(pattern.toString()))
                .parallel()
                .collect(Collectors.toList());
    }

    static String getKeywords(String request) {
        return parseWord(request)
                .stream()
                .parallel()
                .map(eachWord -> parseNGrams(eachWord)
                        .stream()
                        .parallel()
                        .map(eachBi -> String.format("%s %s",
                                eachWord,
                                String.format("*%s*", eachBi)))
                        .collect(Collectors.joining(" ")))
                .collect(Collectors.joining(" "));
    }
}
