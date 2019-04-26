package org.group.projects.simple.gis.dao;

import org.group.projects.simple.gis.model.entity.EntityData;
import org.hibernate.Session;
import org.group.projects.simple.gis.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityDataAccessManager<ENTITY extends EntityData>
        implements EntityDataAccessManager<ENTITY> {

    protected final Class<ENTITY> mTypeParameterClass;

    protected AbstractEntityDataAccessManager(Class<ENTITY> typeParameterClass) {
        this.mTypeParameterClass = typeParameterClass;
    }

    @Override
    public void create(ENTITY entity) {
        Session mSession = HibernateUtil.getSessionFactory().openSession();
        mSession.beginTransaction();
        mSession.save(entity);
        mSession.getTransaction().commit();

        if (mSession.isOpen()) {
            mSession.close();
        }
    }

    @Override
    public void update(ENTITY entity) {
        Session mSession = HibernateUtil.getSessionFactory().openSession();
        mSession.beginTransaction();
        mSession.update(entity);
        mSession.getTransaction().commit();

        if (mSession.isOpen()) {
            mSession.close();
        }
    }

    @Override
    public List<ENTITY> select() {
        Session mSession = HibernateUtil.getSessionFactory().openSession();
        mSession.beginTransaction();
        ArrayList<ENTITY> mResult = (ArrayList<ENTITY>) mSession.createQuery(
                String.format("from %s", mTypeParameterClass.getCanonicalName())
        ).list();
        mSession.getTransaction().commit();

        if (mSession.isOpen()) {
            mSession.close();
        }

        return mResult;
    }

    @Override
    public void delete(ENTITY entity) {
        Session mSession = HibernateUtil.getSessionFactory().openSession();
        mSession.beginTransaction();
        mSession.delete(entity);
        mSession.getTransaction().commit();

        if (mSession.isOpen()) {
            mSession.close();
        }
    }
}