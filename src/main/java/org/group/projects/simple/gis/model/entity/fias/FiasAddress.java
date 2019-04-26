package org.group.projects.simple.gis.model.entity.fias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "d_fias_addrobj")
@NoArgsConstructor
@AllArgsConstructor
public class FiasAddress implements FiasEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aoid")
    @Getter
    @Setter
    private String aoId;

    @Column(name = "formalname")
    @Getter
    @Setter
    private String formalName;

    @Column(name = "regioncode")
    @Getter
    @Setter
    private String regionCode;

    @Column(name = "autocode")
    @Getter
    @Setter
    private String autoCode;

    @Column(name = "areacode")
    @Getter
    @Setter
    private String areaCode;

    @Column(name = "citycode")
    @Getter
    @Setter
    private String cityCode;

    @Column(name = "ctarcode")
    @Getter
    @Setter
    private String ctarCode;

    @Column(name = "placecode")
    @Getter
    @Setter
    private String placeCode;

    @Column(name = "streetcode")
    @Getter
    @Setter
    private String streetCode;

    @Column(name = "extrcode")
    @Getter
    @Setter
    private String extrCode;

    @Column(name = "sextcode")
    @Getter
    @Setter
    private String sextCode;

    @Column(name = "offname")
    @Getter
    @Setter
    private String offName;
  
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
  
    @Column(name = "shortname")
    @Getter
    @Setter
    private String shortName;
  
    @Column(name = "aolevel")
    @Getter
    @Setter
    private Integer aoLevel;

    @Column(name = "aoguid")
    @Getter
    @Setter
    private String aoGuid;

    @Column(name = "previd")
    @Getter
    @Setter
    private String prevId;

    @Column(name = "nextid")
    @Getter
    @Setter
    private String nextId;

    @Column(name = "code")
    @Getter
    @Setter
    private String code;

    @Column(name = "plaincode")
    @Getter
    @Setter
    private String plainCode;

    @Column(name = "actstatus")
    @Getter
    @Setter
    private Byte actStatus;

    @Column(name = "centstatus")
    @Getter
    @Setter
    private Integer centStatus;

    @Column(name = "operstatus")
    @Getter
    @Setter
    private Integer operStatus;

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

    @ManyToOne (fetch=FetchType.EAGER,
            cascade=CascadeType.ALL,
            optional = false)
    @JoinColumn (nullable = false,
            name = "parentguid",
            referencedColumnName = "aoguid")
    @Getter
    @Setter
    private FiasAddress parent;

    @OneToMany(fetch = FetchType.EAGER,
              cascade = CascadeType.ALL,
              mappedBy = "parent")
    @Getter
    @Setter
    private List<FiasAddress> child;

    {
        this.child = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("{\"aoid\" = \"%s\", \"name\" = \"%s\"}",
                this.getAoId(),
                this.getFormalName()
        );
    }
}