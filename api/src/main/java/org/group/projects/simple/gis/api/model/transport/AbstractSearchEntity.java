package org.group.projects.simple.gis.api.model.transport;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@EqualsAndHashCode
public class AbstractSearchEntity implements SearchEntity {

    @Getter
    @Setter
    protected String content;

}
