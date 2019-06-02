package org.group.projects.simple.gis.model.entity;

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
public class Firm implements GeoEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,
            name = "id")
    @Getter
    @Setter
    private int id;

    @Column(nullable = false,
            name = "lon")
    @Getter
    @Setter
    private String lon;

    @Column(nullable = false,
            name = "lat")
    @Getter
    @Setter
    private String lat;

    @Column(nullable = false,
            name = "alias")
    @Getter
    @Setter
    private String alias;

    @Column(nullable = false,
            name = "name")
    @Getter
    @Setter
    private String name;

    @Column(nullable = false,
            name = "city_name")
    @Getter
    @Setter
    private String nameCity;

    @Column(nullable = false,
            name = "geometry_name")
    @Getter
    @Setter
    private String nameGeometry;

    @Column(nullable = false,
            name = "office")
    @Getter
    @Setter
    private String office;

    @Column(nullable = false,
            name = "geometry")
    @Getter
    @Setter
    private String geometry;

    @Column(nullable = false,
            name = "external_id")
    @Getter
    @Setter
    private Long externalId;

    @ManyToMany(mappedBy = "firms")
    @JsonIgnore
    @Getter
    @Setter
    private List<Building> buildings;

    {
        this.buildings = new ArrayList<>();
    }
}