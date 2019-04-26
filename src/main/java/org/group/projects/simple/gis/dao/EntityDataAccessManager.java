package org.group.projects.simple.gis.dao;

import org.group.projects.simple.gis.model.entity.EntityData;

import java.util.List;

public interface EntityDataAccessManager<ENTITY extends EntityData> {

    void create(ENTITY entity);

    void update(ENTITY entity);

    List<ENTITY> select();

    void delete(ENTITY entity);
}
