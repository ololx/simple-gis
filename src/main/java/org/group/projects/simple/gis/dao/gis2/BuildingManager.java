package org.group.projects.simple.gis.dao.gis2;

import org.group.projects.simple.gis.dao.AbstractEntityDataAccessManager;
import org.group.projects.simple.gis.model.entity.gis2.Building;
import org.group.projects.simple.gis.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BuildingManager extends AbstractEntityDataAccessManager<Building> {

    @Autowired
    private SessionFactory sessionFactory;

    public BuildingManager() {
        super(Building.class);
    }

    /*@Autowired
    SessionFactory sessionFactory;*/

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

    public List<Building> selectByFullAddress(String request) {

        //Session session = sessionFactory.openSession();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createNativeQuery("select * from building where match(city, district, street, street2, number, number2, postcode) against('" +
                Stream.of(request.split(" "))
                        .map(eachKeyWord -> "*" + eachKeyWord + "*")
                        .reduce(" ", String::concat)
                + "' IN BOOLEAN MODE)")
                .addEntity(Building.class);
        List<Building> result = query.list();

        session.getTransaction().commit();

        if (session.isOpen()) {
            session.close();
        }

        return result;
    }
}