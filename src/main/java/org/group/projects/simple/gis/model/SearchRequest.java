package org.group.projects.simple.gis.model;

import lombok.*;

@NoArgsConstructor
@ToString(includeFieldNames = true)
public class SearchRequest extends AbstractSearchEntity {

    public SearchRequest(String content) {
        super(content);
    }
}
