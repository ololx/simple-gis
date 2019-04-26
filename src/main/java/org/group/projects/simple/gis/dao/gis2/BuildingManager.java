package org.group.projects.simple.gis.dao.gis2;

import org.group.projects.simple.gis.dao.AbstractEntityDataAccessManager;
import org.group.projects.simple.gis.model.entity.fias.FiasAddress;
import org.group.projects.simple.gis.model.entity.gis2.Building;
import org.group.projects.simple.gis.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class BuildingManager extends AbstractEntityDataAccessManager<Building> {

    public BuildingManager() {
        super(Building.class);
    }

    public List<Building> selectByFormalName(String formalName) {
        Session mSession = HibernateUtil.getSessionFactory().openSession();
        mSession.beginTransaction();
        ArrayList<Building> mResult = (ArrayList<Building>) mSession.createQuery(
                String.format("from %s where %s like '%s'", mTypeParameterClass.getCanonicalName(),
                        "street",
                        "%" + formalName + "%")
        ).list();
        mSession.getTransaction().commit();

        if (mSession.isOpen()) {
            mSession.close();
        }

        return mResult;
    }
}