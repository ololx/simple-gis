package org.group.projects.simple.gis.model.transport;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString(includeFieldNames = true)
public class SearchRequest extends AbstractSearchEntity {

    public SearchRequest(String content) {
        super(content);
    }
}
