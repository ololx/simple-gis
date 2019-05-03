package org.group.projects.simple.gis.repository;

import org.group.projects.simple.gis.model.entity.GeoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public abstract class AbstractGeoEntityManager<ENTITY extends GeoEntity>
        implements GeoEntityManager<ENTITY> {

    @Autowired
    protected SessionFactory sessionFactory;

    protected final Class<ENTITY> typeParameterClass;

    protected AbstractGeoEntityManager(Class<ENTITY> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public void create(ENTITY entity) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();

        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void update(ENTITY entity) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();

        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public List<ENTITY> select() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<ENTITY> mResult = (ArrayList<ENTITY>) session.createQuery(
                String.format("from %s", this.typeParameterClass.getCanonicalName())
        ).list();
        session.getTransaction().commit();

        if (session.isOpen()) {
            session.close();
        }

        return mResult;
    }

    @Override
    public void delete(ENTITY entity) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();

        if (session.isOpen()) {
            session.close();
        }
    }
}
