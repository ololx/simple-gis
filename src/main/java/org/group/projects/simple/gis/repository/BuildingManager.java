package org.group.projects.simple.gis.repository;

import org.group.projects.simple.gis.model.entity.Building;
import org.group.projects.simple.gis.service.GeoInformationService;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Transactional
public class BuildingManager extends AbstractGeoEntityManager<Building> {

    public BuildingManager() {
        super(Building.class);
    }

    public List<Building> selectByFormalName(String formalName) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Building> result = (ArrayList<Building>) session.createQuery(
                String.format("from %s where %s like '%s'", this.typeParameterClass.getCanonicalName(),
                        "street",
                        "%" + formalName + "%")
        ).list();
        session.getTransaction().commit();

        if (session.isOpen()) {
            session.close();
        }

        return result;
    }

    public List<Building> selectByFullAddress(String request) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        String[] fields = {"city", "district", "street", "street2", "number", "number2", "postcode"};
        SQLQuery query = session.createNativeQuery(
                String.format("select * from %s where match(%s) against('%s' IN BOOLEAN MODE) LIMIT 30",
                        "building",
                        Stream.of(fields).collect(Collectors.joining(", ")),
                        request
                )
        ).addEntity(Building.class);
        List<Building> result = query.list();

        session.getTransaction().commit();

        if (session.isOpen()) {
            session.close();
        }

        return result;
    }
}