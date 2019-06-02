package org.group.projects.simple.gis.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.group.projects.simple.gis.model.entity.Firm;
import org.group.projects.simple.gis.model.entity.GeoEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("lon")
    private String lon;

    @JsonProperty("lat")
    private String lat;
}