package org.group.projects.simple.gis.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "d_fias_house")
@NoArgsConstructor
@AllArgsConstructor
public class HouseObject implements FiasEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "houseid")
    @Getter
    @Setter
    private String houseId;

    @Column(name = "postalcode")
    @Getter
    @Setter
    private String postalCode;

    @Column(name = "ifnsfl")
    @Getter
    @Setter
    private String ifnsflCode;

    @Column(name = "terrifnsfl")
    @Getter
    @Setter
    private String terrifnsflCode;

    @Column(name = "ifnsul")
    @Getter
    @Setter
    private String ifnsulCode;

    @Column(name = "terrifnsul")
    @Getter
    @Setter
    private String terrifnsulCode;

    @Column(name = "okato")
    @Getter
    @Setter
    private String okato;

    @Column(name = "oktmo")
    @Getter
    @Setter
    private String oktmo;

    @Column(name = "updatedate")
    @Getter
    @Setter
    private Date updateDate;

    @Column(name = "housenum")
    @Getter
    @Setter
    private String houseNum;

    @Column(name = "eststatus")
    @Getter
    @Setter
    private Integer estStatus;

    @Column(name = "buildnum")
    @Getter
    @Setter
    private String buildNum;

    @Column(name = "strucnum")
    @Getter
    @Setter
    private Integer strucNum;

    @Column(name = "strstatus")
    @Getter
    @Setter
    private Integer strStatus;
  
    @Column(name = "houseguid")
    @Getter
    @Setter
    private String houseGuid;

    /*@Column(name = "aoguid")
    @Getter
    @Setter
    private String aoGuid;*/

    @Column(name = "currstatus")
    @Getter
    @Setter
    private Integer currStatus;

    @Column(name = "startdate")
    @Getter
    @Setter
    private Date startDate;

    @Column(name = "enddate")
    @Getter
    @Setter
    private Date endDate;

    @Column(name = "normdoc")
    @Getter
    @Setter
    private String normDoc;

    @Column(name = "counter")
    @Getter
    @Setter
    private String counter;

    @ManyToOne (fetch=FetchType.EAGER,
            cascade=CascadeType.ALL,
            optional = false)
    @JoinColumn (nullable = false,
            name = "aoguid",
            referencedColumnName = "aoguid")
    @Getter
    @Setter
    private AddressObject addressObject;

    @Override
    public String toString() {
        return String.format("{\"houseid\" = \"%s\", \"name\" = \"%s\"}",
                this.getHouseId(),
                this.getHouseNum()
        );
    }
}