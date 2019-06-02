package org.group.projects.simple.gis.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Building")
@Table(name = "building")
@NamedNativeQuery(
        name = "Building.findBuildingViaIndex",
        query = "select *, match(city, district, street, street2, number, number2, postcode) " +
                "against(:value IN BOOLEAN MODE) as relevance " +
                "from building " +
                "where match(city, district, street, street2, number, number2, postcode) " +
                "against(:value IN BOOLEAN MODE) " +
                "order by relevance desc",
        resultClass=Building.class
)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(
        includeFieldNames = true,
        exclude = {
                "firms"
        }
)
@EqualsAndHashCode(exclude = {
        "id1", "id2"
    }
)
public class Building implements GeoEntity, Serializable {

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
            name = "city")
    @Getter
    @Setter
    private String city;

    @Column(nullable = false,
            name = "district")
    @Getter
    @Setter
    private String district;

    @Column(nullable = false,
            name = "street")
    @Getter
    @Setter
    private String street;

    @Column(nullable = false,
            name = "number")
    @Getter
    @Setter
    private String number;

    @Column(nullable = false,
            name = "street2")
    @Getter
    @Setter
    @JsonProperty("street2")
    private String streetAdditional;

    @Column(nullable = false,
            name = "number2")
    @Getter
    @Setter
    @JsonProperty("number2")
    private String numberAdditional;

    @Column(nullable = false,
            name = "buildingname")
    @Getter
    @Setter
    @JsonProperty("buildingname")
    private String buildingName;

    @Column(nullable = false,
            name = "purpose")
    @Getter
    @Setter
    private String purpose;

    @Column(nullable = false,
            name = "elevation")
    @Getter
    @Setter
    private String elevation;

    @Column(nullable = false,
            name = "firmcount")
    @Getter
    @Setter
    @JsonProperty("firmcount")
    private String firmCount;

    @Column(nullable = false,
            name = "postcode")
    @Getter
    @Setter
    @JsonProperty("postcode")
    private String postCode;

    @Column(nullable = false,
            name = "external_id")
    @Getter
    @Setter
    @JsonProperty("external_id")
    private Long externalId;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            }
            )
    @JoinTable(name = "firm_to_building",
            joinColumns = {
                    @JoinColumn(nullable = true,
                    name = "building_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "firm_id")
            }
    )
    @JsonIgnore
    @Getter
    @Setter
    private List<Firm> firms;

    {
        this.firms = new ArrayList<>();
    }

    public String getAddress() {
        return String.format("%s, %s-%s",
                this.getCity(),
                this.getStreet(),
                this.getNumber());
    }
}