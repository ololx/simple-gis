package org.group.projects.simple.gis.dao;

import org.group.projects.simple.gis.model.entity.FiasEntity;

import java.util.List;

public interface EntityDataAccessManager<ENTITY extends FiasEntity> {

    void create(ENTITY entity);

    void update(ENTITY entity);

    List<ENTITY> select();

    void delete(ENTITY entity);
}
