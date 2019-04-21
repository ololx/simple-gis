package org.group.projects.simple.gis.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "d_fias_addrobj")
public class AddressObject
        implements FiasEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aoid")
    private Integer mAoid;

    @Column(name = "formalname")
    private String mFormalName;

    /*@Column(name = "regioncode")
    private String mRegionCode;

    @Column(name = "autocode")
    private String mAutoCode;

    @Column(name = "areacode")
    private String mAreaCode;

    @Column(name = "citycode")
    private String mCityCode;*/

    public AddressObject() {}

    public AddressObject(Integer aoid, String formalname) {
        this.setId(aoid);
        this.setFormalName(formalname);
    }

    @Override
    public String toString() {
        return String.format("{\"aoid\" = \"%s\", \"formalname\" = \"%s\"}",
                this.setAoid(),
                this.getFormalName()
        );
    }

    public Integer getAoid() {
        return this.mAoid;
    }

    public void setAoid(Integer aoid) {
        this.mAoid = aoid;
    }

    public String getFormalName() {
        return this.mFormalName;
    }

    public void setFormalName(String content) {
        this.mFormalName = content;
    }
}