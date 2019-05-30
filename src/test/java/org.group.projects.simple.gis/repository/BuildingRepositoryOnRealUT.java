package org.group.projects.simple.gis.repository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.categories.OnRealTest;
import org.group.projects.simple.gis.categories.UnitTest;
import org.group.projects.simple.gis.model.entity.Building;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;

@Category(OnRealTest.class)
@ActiveProfiles("test-on-real")
@Slf4j
@NoArgsConstructor
public class BuildingRepositoryOnRealUT extends BuildingRepositoryUT {

    @Test
    public void findBuildingViaIndex() {
        log.info("[BuildingRepository]: start search building against - '*ново*'");
        List<Building> result = buildingRepository.findBuildingViaIndex("*ново*",
                new PageRequest(0, 3));
        log.info(String.format("[BuildingRepository]: search result = %s",
                (result.parallelStream()
                        .map(eachBuilding -> eachBuilding.toString())
                        .collect(Collectors.joining(", ")))));
    }
}
