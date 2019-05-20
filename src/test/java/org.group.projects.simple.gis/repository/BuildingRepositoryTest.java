package org.group.projects.simple.gis.repository;

import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.Application;
import org.group.projects.simple.gis.model.entity.Building;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;

@DirtiesContext
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class BuildingRepositoryTest  {

    @Autowired
    private BuildingRepository buildingRepository;

    @DisplayName("Repository findBuildingViaIndex test")
    @Test
    public void findBuildingViaIndex() {
        log.info("[BuildingRepository]: stast search buildings against - '*ново*'");
        List<Building> result = buildingRepository.findBuildingViaIndex("*ново*");
        log.info(String.format("[BuildingRepository]: search result = %s",
                result.stream()
                        .map(eachBulding -> eachBulding.toString())
                        .collect(Collectors.joining(", "))));
    }
}
