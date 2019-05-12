package org.group.projects.simple.gis.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class AbstractSearchEntity implements SearchEntity {

    @Getter
    @Setter
    protected String content;

}
