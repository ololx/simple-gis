package org.group.projects.simple.gis.dao;

import org.group.projects.simple.gis.model.entity.GeoEntity;

import java.util.List;

public interface GeoEntityManager<ENTITY extends GeoEntity> {

    void create(ENTITY entity);

    void update(ENTITY entity);

    List<ENTITY> select();

    void delete(ENTITY entity);
}
