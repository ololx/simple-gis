package org.group.projects.simple.gis.dao.fias;

import org.group.projects.simple.gis.dao.AbstractEntityDataAccessManager;
import org.group.projects.simple.gis.model.entity.GeoEntity;
import org.group.projects.simple.gis.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AddressObjectManager extends AbstractEntityDataAccessManager<GeoEntity> {

    public AddressObjectManager() {
        super(GeoEntity.class);
    }

    public List<GeoEntity> selectByFormalName(String formalName) {
        Session mSession = HibernateUtil.getSessionFactory().openSession();
        mSession.beginTransaction();
        ArrayList<GeoEntity> mResult = (ArrayList<GeoEntity>) mSession.createQuery(
                String.format("from %s where %s like '%s' and %s = 1", mTypeParameterClass.getCanonicalName(),
                        "formalname",
                        formalName + "%",
                        "actstatus")
        ).list();
        mSession.getTransaction().commit();

        if (mSession.isOpen()) {
            mSession.close();
        }

        return mResult;
    }
}