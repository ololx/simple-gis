package org.group.projects.simple.gis.api.repository;

import org.group.projects.simple.gis.api.model.entity.Building;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface BuildingRepository extends CrudRepository<Building, Integer> {
    List<Building> findBuildingViaIndex(@Param("value") String value, Pageable pageable);

    List<Building> findBuildingViaIndex(@Param("value") String value);

    Building findBuildingById(int id);
}
