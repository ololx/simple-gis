package org.group.projects.simple.gis.service;

import org.group.projects.simple.gis.model.entity.Building;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

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
}
