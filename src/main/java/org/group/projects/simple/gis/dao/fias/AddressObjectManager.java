package org.group.projects.simple.gis.dao.fias;

import org.group.projects.simple.gis.dao.AbstractEntityDataAccessManager;
import org.group.projects.simple.gis.model.entity.EntityData;
import org.group.projects.simple.gis.model.entity.fias.FiasAddress;
import org.group.projects.simple.gis.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AddressObjectManager extends AbstractEntityDataAccessManager<EntityData> {

    public AddressObjectManager() {
        super(EntityData.class);
    }

    public List<EntityData> selectByFormalName(String formalName) {
        Session mSession = HibernateUtil.getSessionFactory().openSession();
        mSession.beginTransaction();
        ArrayList<EntityData> mResult = (ArrayList<EntityData>) mSession.createQuery(
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