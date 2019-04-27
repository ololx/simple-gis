package org.group.projects.simple.gis.model.entity.gis2;

import lombok.*;
import org.group.projects.simple.gis.model.entity.EntityData;
import org.group.projects.simple.gis.model.entity.fias.FiasEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "building")
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames=true)
public class Building implements EntityData, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,
            name = "id")
    @Getter
    @Setter
    private String id;

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
    private String streetAdditional;

    @Column(nullable = false,
            name = "number2")
    @Getter
    @Setter
    private String numberAdditional;

    @Column(nullable = false,
            name = "buildingname")
    @Getter
    @Setter
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
    private String firmCount;

    @Column(nullable = false,
            name = "postcode")
    @Getter
    @Setter
    private String postCode;

    @Column(nullable = false,
            name = "external_id")
    @Getter
    @Setter
    private Long externalId;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "firm_to_building",
            joinColumns = {
                    @JoinColumn(nullable = false,
                    name = "building_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "firm_id")
            }
    )
    @Getter
    @Setter
    List<Building> firms;

    {
        this.firms = new ArrayList<>();
    }
}