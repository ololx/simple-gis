package org.group.projects.simple.gis.repository;

import org.group.projects.simple.gis.model.entity.Building;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BuildingRepository extends Repository<Building, Long> {
    List<Building> findBuildingViaIndex(@Param("value") String value,  Pageable pageable);

    List<Building> findBuildingViaIndex(@Param("value") String value);

    Building findBuildingById(@Param("id") long id);
}
