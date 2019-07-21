package org.group.projects.simple.gis.online.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Firm")
@Table(name = "firm")
@NoArgsConstructor
@AllArgsConstructor
@ToString(
        includeFieldNames = true,
        exclude = {
                "buildings"
    }
)
@EqualsAndHashCode(exclude = {
        "id1", "id2"
    }
)
@Getter
@Setter
public class Firm implements GeoEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,
            name = "id")
    private int id;

    @Column(nullable = false,
            name = "lon")
    private String lon;

    @Column(nullable = false,
            name = "lat")
    private String lat;

    @Column(nullable = false,
            name = "alias")
    private String alias;

    @Column(nullable = false,
            name = "name")
    private String name;

    @Column(nullable = false,
            name = "city_name")
    private String nameCity;

    @Column(nullable = false,
            name = "geometry_name")
    private String nameGeometry;

    @Column(nullable = false,
            name = "office")
    private String office;

    @Column(nullable = false,
            name = "geometry")
    private String geometry;

    @Column(nullable = false,
            name = "external_id")
    private Long externalId;

    @ManyToMany(mappedBy = "firms")
    @JsonIgnore
    private List<Building> buildings;

    {
        this.buildings = new ArrayList<>();
    }
}