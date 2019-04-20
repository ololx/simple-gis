package org.group.projects.simple.gis.controller;

import javax.persistence.Entity;
import javax.persistence.Id;

public class SearchResult {

    private int id;
    private String content;

    public SearchResult() {}

    public SearchResult(int id, String content) {
       this.setId(id);
       this.setContent(content);
    }

    @Override
    public String toString() {
        return String.format("{\"id\" = \"%s\", \"content\" = \"%s\"}",
                this.getId(),
                this.getContent()
        );
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
