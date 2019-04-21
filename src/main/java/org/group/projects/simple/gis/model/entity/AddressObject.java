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
    private Integer mId;

    @Column(name = "formalname")
    private String mFormalName;

    public AddressObject() {}

    public AddressObject(Integer aoid, String formalname) {
        this.setId(aoid);
        this.setFormalName(formalname);
    }

    @Override
    public String toString() {
        return String.format("{\"id\" = \"%s\", \"content\" = \"%s\"}",
                this.getId(),
                this.getFormalName()
        );
    }

    public Integer getId() {
        return this.mId;
    }

    public void setId(Integer aoid) {
        this.mId = aoid;
    }

    public String getFormalName() {
        return this.mFormalName;
    }

    public void setFormalName(String content) {
        this.mFormalName = content;
    }
}