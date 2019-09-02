package org.group.projects.simple.gis.api.model.transport;

import lombok.*;
import org.group.projects.simple.gis.api.model.entity.Building;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class SearchResult implements Serializable {

    private List<Building> results;

}
