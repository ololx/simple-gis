package org.group.projects.simple.gis.dao;

import org.group.projects.simple.gis.model.entity.Building;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Repository
@Transactional
public class BuildingManager extends AbstractEntityDataAccessManager<Building> {

    @Autowired
    private SessionFactory sessionFactory;

    public BuildingManager() {
        super(Building.class);
    }

    public List<Building> selectByFormalName(String formalName) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Building> mResult = (ArrayList<Building>) session.createQuery(
                String.format("from %s where %s like '%s'", mTypeParameterClass.getCanonicalName(),
                        "street",
                        "%" + formalName + "%")
        ).list();
        session.getTransaction().commit();

        if (session.isOpen()) {
            session.close();
        }

        return mResult;
    }

    public List<Building> selectByFullAddress(String request) {

        //Session session = this.sessionFactory.getCurrentSession();
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        SQLQuery<Building> query = session.createNativeQuery("select * from building where match(city, district, street, street2, number, number2, postcode) against('" +
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