package org.group.projects.simple.gis.dao;

import org.group.projects.simple.gis.model.entity.AddressObject;
import org.group.projects.simple.gis.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AddressObjectManager extends AbstractFiasEntityDataAccessManager<AddressObject> {

    public AddressObjectManager() {
        super(AddressObject.class);
    }

    public List<AddressObject> selectByFormalName(String formalName) {
        Session mSession = HibernateUtil.getSessionFactory().openSession();
        mSession.beginTransaction();
        ArrayList<AddressObject> mResult = (ArrayList<AddressObject>) mSession.createQuery(
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