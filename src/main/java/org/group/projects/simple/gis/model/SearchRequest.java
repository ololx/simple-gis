package org.group.projects.simple.gis.model;

import jdk.internal.jline.internal.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;

public class SearchRequest {

    @Nullable
    private String content;

    public SearchRequest() {}

    public SearchRequest(String content) {
       this.setContent(content);
    }

    @Override
    public String toString() {
        return String.format("{\"content\" = \"%s\"}",
                this.getContent()
        );
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
