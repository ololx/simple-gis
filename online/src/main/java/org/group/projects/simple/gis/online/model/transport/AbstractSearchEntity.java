package org.group.projects.simple.gis.online.model.transport;

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
