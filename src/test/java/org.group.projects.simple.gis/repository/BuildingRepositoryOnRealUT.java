package org.group.projects.simple.gis.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.categories.OnRealTest;
import org.group.projects.simple.gis.model.entity.Building;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.Collectors;

@Category(OnRealTest.class)
@ActiveProfiles("test-on-real")
@Slf4j
@NoArgsConstructor
public class BuildingRepositoryOnRealUT extends BuildingRepositoryUT {

    @Test
    @Sql("classpath:building.sql")
    @Value("classpath:building.sql")
    public void findBuildingViaIndex() {
        log.info("[BuildingRepository]: start search building against - '*ново*'");
        List<Building> result = buildingRepository.findBuildingViaIndex("*ново*");
        log.info(String.format("[BuildingRepository]: search result = %s",
                (result.parallelStream()
                        .map(eachBuilding -> eachBuilding.toString())
                        .collect(Collectors.joining(", ")))));
    }
}
