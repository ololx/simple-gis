package org.group.projects.simple.gis.dao;

import org.group.projects.simple.gis.model.entity.fias.FiasAddress;
import org.group.projects.simple.gis.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AddressObjectManager extends AbstractFiasEntityDataAccessManager<FiasAddress> {

    public AddressObjectManager() {
        super(FiasAddress.class);
    }

    public List<FiasAddress> selectByFormalName(String formalName) {
        Session mSession = HibernateUtil.getSessionFactory().openSession();
        mSession.beginTransaction();
        ArrayList<FiasAddress> mResult = (ArrayList<FiasAddress>) mSession.createQuery(
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