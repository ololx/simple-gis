package org.group.projects.simple.gis.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Getter
@Setter
public class Building implements GeoEntity, Serializable {

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
            name = "postcode")
    private String postCode;

    @Column(nullable = false,
            name = "city")
    private String city;

    @Column(nullable = false,
            name = "district")
    private String district;

    @Column(nullable = false,
            name = "street")
    private String street;

    @Column(nullable = false,
            name = "number")
    private String number;

    @Column(nullable = false,
            name = "street2")
    private String streetOther;

    @Column(nullable = false,
            name = "number2")
    private String numberOther;

    @Column(nullable = false,
            name = "buildingname")
    private String name;

    @Column(nullable = false,
            name = "purpose")
    private String purpose;

    @Column(nullable = false,
            name = "elevation")
    private String elevation;

    @Column(nullable = false,
            name = "firmcount")
    private String firmCount;

    @Column(nullable = false,
            name = "external_id")
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