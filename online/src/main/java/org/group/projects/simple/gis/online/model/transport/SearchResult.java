package org.group.projects.simple.gis.online.model.transport;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class SearchResult extends AbstractSearchEntity implements Serializable {

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString(includeFieldNames = true)
    public static class Result implements Serializable {

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

    public SearchResult(List<Result> results, String content) {
        super(content);
        this.results = results;
    }

    public void addResult(Result result) {
        this.results.add(result);
    }
}
