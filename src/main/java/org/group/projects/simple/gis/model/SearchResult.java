package org.group.projects.simple.gis.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class SearchResult extends SearchRequest {

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString(includeFieldNames = true)
    public static class Result {

        @Getter
        @Setter
        private String lon;

        @Getter
        @Setter
        private String lat;

        @Getter
        @Setter
        private String address;

    }

    @Getter
    @Setter
    private List<Result> results;

    {
        this.results = new ArrayList<>();
    }

    public void addResult(Result result) {
        this.results.add(result);
    }
}
